package br.com.cesarschool.documentos.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cesarschool.documentos.dominio.Cliente;
import br.com.cesarschool.documentos.dominio.ComprovanteResidencia;
import br.com.cesarschool.documentos.dominio.ResultadoValidacao;
import br.com.cesarschool.documentos.dominio.StatusValidacao;
import br.com.cesarschool.documentos.dto.DadosComprovanteDto;
import br.com.cesarschool.documentos.dto.EtapaPipeline;
import br.com.cesarschool.documentos.dto.ValidacaoRequest;
import br.com.cesarschool.documentos.dto.ValidacaoResponse;
import br.com.cesarschool.documentos.exception.ClienteNaoEncontradoException;
import br.com.cesarschool.documentos.regras.Regra;
import br.com.cesarschool.documentos.regras.ResultadoRegra;
import br.com.cesarschool.documentos.repository.ClienteRepository;
import br.com.cesarschool.documentos.repository.ValidacaoRepository;
import br.com.cesarschool.documentos.service.InterpretadorIaService.Interpretacao;
import br.com.cesarschool.documentos.service.OcrService.ResultadoOcr;
import br.com.cesarschool.documentos.service.OpenCvService.ImagemTratada;

/** Orquestra o pipeline: Documento → OpenCV → Tesseract → IA → Regras → banco. */
@Service
public class ValidacaoService 
{

    private final ClienteRepository clienteRepository;
    private final ValidacaoRepository validacaoRepository;
    private final OpenCvService openCvService;
    private final OcrService ocrService;
    private final InterpretadorIaService interpretadorIaService;
    private final List<Regra> regras;
    private final ObjectMapper objectMapper;
    private final Clock clock;

    public ValidacaoService(ClienteRepository clienteRepository, 
    ValidacaoRepository validacaoRepository, OpenCvService openCvService, 
    OcrService ocrService, InterpretadorIaService interpretadorIaService,
    List<Regra> regras, ObjectMapper objectMapper, Clock clock) 
    {
        this.clienteRepository = clienteRepository;
        this.validacaoRepository = validacaoRepository;
        this.openCvService = openCvService;
        this.ocrService = ocrService;
        this.interpretadorIaService = interpretadorIaService;
        this.regras = regras;
        this.objectMapper = objectMapper;
        this.clock = clock;
    }

    @Transactional
    public ValidacaoResponse validarComprovante(Long clienteId, ValidacaoRequest request) 
    {
        Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));

        boolean temTexto = request.conteudoDocumento() != null && !request.conteudoDocumento().isBlank();
        boolean temImagem = request.imagemBase64() != null && !request.imagemBase64().isBlank();

        if (!temTexto && !temImagem) 
        {
            throw new IllegalArgumentException("Envie conteudoDocumento (texto) e/ou imagemBase64.");
        }

        List<EtapaPipeline> etapas = new ArrayList<>();
        String nomeArquivo = request.nomeArquivo() != null ? request.nomeArquivo() : "comprovante";
        etapas.add(new EtapaPipeline(1, "Documento", nomeArquivo));

        // 2. OpenCV
        ImagemTratada imagem = null;
        if (temImagem) 
        {
            imagem = openCvService.tratar(decodificar(request.imagemBase64()), extensao(request));
            etapas.add(new EtapaPipeline(2, "OpenCV", imagem.descricao()));
        } 
        else 
        {
            etapas.add(new EtapaPipeline(2, "OpenCV", "Pulado — nenhuma imagem enviada"));
        }

        // 3. Tesseract
        ResultadoOcr ocr = ocrService.extrair(imagem, request.conteudoDocumento());
        etapas.add(new EtapaPipeline(3, "Tesseract", ocr.descricao()));

        // 4. IA
        Interpretacao interpretacao = interpretadorIaService.interpretar(ocr.texto());
        etapas.add(new EtapaPipeline(4, "IA", interpretacao.descricao()));

        // 5. Regras
        DadosComprovanteDto d = interpretacao.dados();
        ComprovanteResidencia comprovante = new ComprovanteResidencia(d.nomeTitular(), d.endereco(), d.bairro(),
                d.cidadeUf(), d.cep(), d.empresaEmissora(), d.dataEmissao(), ocr.texto(), ocr.confianca());
        List<ResultadoRegra> resultados = regras.stream().map(r -> r.avaliar(comprovante, cliente)).toList();
        List<String> atendidas = resultados.stream().filter(ResultadoRegra::isAtendida)
                .map(ResultadoRegra::descricao).toList();
        List<String> motivos = resultados.stream().filter(ResultadoRegra::isFalhou)
                .map(ResultadoRegra::motivo).toList();
        StatusValidacao status = statusFinal(resultados);
        etapas.add(new EtapaPipeline(5, "Regras", "%d de %d regras atendidas".formatted(atendidas.size(), regras.size())));

        // 6. Banco
        ResultadoValidacao salvo = validacaoRepository.save(new ResultadoValidacao(cliente, nomeArquivo, comprovante,
                status, json(atendidas), json(motivos), json(etapas), LocalDateTime.now(clock)));
        return paraResponse(salvo);
    }

    @Transactional(readOnly = true)
    public ValidacaoResponse buscar(Long clienteId, Long validacaoId) 
    {
        return validacaoRepository.findByIdAndClienteId(validacaoId, clienteId)
        .map(this::paraResponse)
        .orElseThrow(() -> new NoSuchElementException(
    "Validação " + validacaoId + " não encontrada para o cliente " + clienteId + "."));
    }

    @Transactional(readOnly = true)
    public List<ValidacaoResponse> listar() 
    {
        return validacaoRepository.findAllByOrderByCriadoEmDesc().stream().map(this::paraResponse).toList();
    }

    /** Qualquer falha que reprova → REPROVADO; senão, alguma falha → ANALISE_MANUAL; senão APROVADO. */
    static StatusValidacao statusFinal(List<ResultadoRegra> resultados) 
    {
        List<StatusValidacao> falhas = resultados.stream().
        filter(ResultadoRegra::isFalhou)
        .map(ResultadoRegra::statusSeFalhar).toList();

        if (falhas.contains(StatusValidacao.REPROVADO)) 
        {
            return StatusValidacao.REPROVADO;
        }

        if (falhas.contains(StatusValidacao.ANALISE_MANUAL)) 
        {
        return StatusValidacao.ANALISE_MANUAL;
        }

        return StatusValidacao.APROVADO;
    }

    private ValidacaoResponse paraResponse(ResultadoValidacao v) 
    {
        return new ValidacaoResponse(
                v.getId(),
                v.getCliente().getId(),
                v.getCliente().getNome(),
                v.getNomeArquivo(),
                v.getCriadoEm(),
                lerJson(v.getEtapas(), new TypeReference<List<EtapaPipeline>>() {}),
                new DadosComprovanteDto(v.getNomeTitular(), v.getEndereco(), v.getBairro(), v.getCidadeUf(),
                v.getCep(), v.getEmpresaEmissora(), v.getDataEmissao()),
                new ValidacaoResponse.Resultado(v.getStatus(),
                lerJson(v.getRegrasAtendidas(), new TypeReference<List<String>>() {}),
                lerJson(v.getMotivos(), new TypeReference<List<String>>() {})));
    }

    private static byte[] decodificar(String base64) 
    {
        String limpo = base64.contains(",") ? base64.substring(base64.indexOf(',') + 1) : base64;
        try 
        {
            return Base64.getMimeDecoder().decode(limpo);
        } 
        catch (IllegalArgumentException e) 
        {
            throw new IllegalArgumentException("imagemBase64 não é um base64 válido.");
        }
    }

    private static String extensao(ValidacaoRequest r) 
    {
        String mime = r.mimeType() == null ? "" : r.mimeType().toLowerCase();
        String nome = r.nomeArquivo() == null ? "" : r.nomeArquivo().toLowerCase();

        if (mime.contains("pdf") || nome.endsWith(".pdf")) 
        {
        return ".pdf";
        }

        if (mime.contains("png") || nome.endsWith(".png")) 
        {
            return ".png";
        }
        return ".jpg";
    }

    private String json(Object valor) 
    {
        try 
        {
            return objectMapper.writeValueAsString(valor);
        } 
        catch (JsonProcessingException e) 
        {
            throw new IllegalStateException(e);
        }
    }

    private <T> T lerJson(String json, TypeReference<T> tipo) 
    {
        try 
        {
            return objectMapper.readValue(json, tipo);
        } 
        catch (JsonProcessingException e) 
        {
            throw new IllegalStateException(e);
        }
    }
}