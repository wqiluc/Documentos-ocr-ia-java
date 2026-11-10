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

    private static final String NOME_ARQUIVO_PADRAO = "comprovante";

    private static final TypeReference<List<String>> LISTA_DE_TEXTOS = new TypeReference<>() {};
    private static final TypeReference<List<EtapaPipeline>> LISTA_DE_ETAPAS = new TypeReference<>() {};

    private final ClienteRepository clienteRepository;
    private final ValidacaoRepository validacaoRepository;
    private final OpenCvService openCvService;
    private final OcrService ocrService;
    private final InterpretadorIaService interpretadorIaService;
    private final List<Regra> regras;
    private final ObjectMapper objectMapper;
    private final Clock clock;

    public ValidacaoService(ClienteRepository clienteRepository,
            ValidacaoRepository validacaoRepository,
            OpenCvService openCvService,
            OcrService ocrService,
            InterpretadorIaService interpretadorIaService,
            List<Regra> regras,
            ObjectMapper objectMapper,
            Clock clock)
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
    public ValidacaoResponse validarComprovante(Long clienteId, ValidacaoRequest requisicao)
    {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));

        boolean enviouTexto = estaPreenchido(requisicao.conteudoDocumento());
        boolean enviouImagem = estaPreenchido(requisicao.imagemBase64());

        if (!enviouTexto && !enviouImagem)
        {
            throw new IllegalArgumentException("Envie conteudoDocumento (texto) e/ou imagemBase64.");
        }

        List<EtapaPipeline> etapas = new ArrayList<>();

        // 1. Documento
        String nomeArquivo = requisicao.nomeArquivo() != null ? requisicao.nomeArquivo() : NOME_ARQUIVO_PADRAO;
        etapas.add(new EtapaPipeline(1, "Documento", nomeArquivo));

        // 2. OpenCV
        ImagemTratada imagemTratada = null;
        if (enviouImagem)
        {
            byte[] conteudoDaImagem = decodificarBase64(requisicao.imagemBase64());
            imagemTratada = openCvService.tratar(conteudoDaImagem, identificarExtensao(requisicao));
            etapas.add(new EtapaPipeline(2, "OpenCV", imagemTratada.descricao()));
        }
        else
        {
            etapas.add(new EtapaPipeline(2, "OpenCV", "Pulado — nenhuma imagem enviada"));
        }

        // 3. Tesseract
        ResultadoOcr resultadoOcr = ocrService.extrair(imagemTratada, requisicao.conteudoDocumento());
        etapas.add(new EtapaPipeline(3, "Tesseract", resultadoOcr.descricao()));

        // 4. IA
        Interpretacao interpretacao = interpretadorIaService.interpretar(resultadoOcr.texto());
        etapas.add(new EtapaPipeline(4, "IA", interpretacao.descricao()));

        // 5. Regras
        ComprovanteResidencia comprovante = montarComprovante(interpretacao.dados(), resultadoOcr);
        List<ResultadoRegra> resultadosDasRegras = regras.stream()
                .map(regra -> regra.avaliar(comprovante, cliente))
                .toList();

        List<String> regrasAtendidas = resultadosDasRegras.stream()
                .filter(ResultadoRegra::isAtendida)
                .map(ResultadoRegra::descricao)
                .toList();

        List<String> motivos = resultadosDasRegras.stream()
                .filter(ResultadoRegra::isFalhou)
                .map(ResultadoRegra::motivo)
                .toList();

        StatusValidacao status = statusFinal(resultadosDasRegras);
        etapas.add(new EtapaPipeline(5, "Regras",
                "%d de %d regras atendidas".formatted(regrasAtendidas.size(), regras.size())));

        // 6. Banco
        ResultadoValidacao validacaoSalva = validacaoRepository.save(new ResultadoValidacao(
                cliente, nomeArquivo, comprovante, status,
                paraJson(regrasAtendidas), paraJson(motivos), paraJson(etapas),
                LocalDateTime.now(clock)));

        return paraResponse(validacaoSalva);
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
        return validacaoRepository.findAllByOrderByCriadoEmDesc().stream()
                .map(this::paraResponse)
                .toList();
    }

    /** Qualquer falha que reprova → REPROVADO; senão, alguma falha → ANALISE_MANUAL; senão APROVADO. */
    static StatusValidacao statusFinal(List<ResultadoRegra> resultadosDasRegras)
    {
        List<StatusValidacao> statusDasFalhas = resultadosDasRegras.stream()
                .filter(ResultadoRegra::isFalhou)
                .map(ResultadoRegra::statusSeFalhar)
                .toList();

        if (statusDasFalhas.contains(StatusValidacao.REPROVADO))
        {
            return StatusValidacao.REPROVADO;
        }

        if (statusDasFalhas.contains(StatusValidacao.ANALISE_MANUAL))
        {
            return StatusValidacao.ANALISE_MANUAL;
        }

        return StatusValidacao.APROVADO;
    }

    private static ComprovanteResidencia montarComprovante(DadosComprovanteDto dadosExtraidos, ResultadoOcr resultadoOcr)
    {
        return new ComprovanteResidencia(
                dadosExtraidos.nomeTitular(),
                dadosExtraidos.endereco(),
                dadosExtraidos.bairro(),
                dadosExtraidos.cidadeUf(),
                dadosExtraidos.cep(),
                dadosExtraidos.empresaEmissora(),
                dadosExtraidos.dataEmissao(),
                resultadoOcr.texto(),
                resultadoOcr.confianca());
    }

    private ValidacaoResponse paraResponse(ResultadoValidacao validacao)
    {
        DadosComprovanteDto dadosExtraidos = new DadosComprovanteDto(
                validacao.getNomeTitular(),
                validacao.getEndereco(),
                validacao.getBairro(),
                validacao.getCidadeUf(),
                validacao.getCep(),
                validacao.getEmpresaEmissora(),
                validacao.getDataEmissao());

        ValidacaoResponse.Resultado resultado = new ValidacaoResponse.Resultado(
                validacao.getStatus(),
                lerJson(validacao.getRegrasAtendidas(), LISTA_DE_TEXTOS),
                lerJson(validacao.getMotivos(), LISTA_DE_TEXTOS));

        return new ValidacaoResponse(
                validacao.getId(),
                validacao.getCliente().getId(),
                validacao.getCliente().getNome(),
                validacao.getNomeArquivo(),
                validacao.getCriadoEm(),
                lerJson(validacao.getEtapas(), LISTA_DE_ETAPAS),
                dadosExtraidos,
                resultado);
    }

    private static boolean estaPreenchido(String valor)
    {
        return valor != null && !valor.isBlank();
    }

    /** Aceita base64 puro ou no formato data URL ("data:image/png;base64,..."). */
    private static byte[] decodificarBase64(String imagemBase64)
    {
        String conteudoBase64 = imagemBase64.contains(",")
                ? imagemBase64.substring(imagemBase64.indexOf(',') + 1)
                : imagemBase64;

        try
        {
            return Base64.getMimeDecoder().decode(conteudoBase64);
        }
        catch (IllegalArgumentException erro)
        {
            throw new IllegalArgumentException("imagemBase64 não é um base64 válido.");
        }
    }

    private static String identificarExtensao(ValidacaoRequest requisicao)
    {
        String mimeType = requisicao.mimeType() == null ? "" : requisicao.mimeType().toLowerCase();
        String nomeArquivo = requisicao.nomeArquivo() == null ? "" : requisicao.nomeArquivo().toLowerCase();

        if (mimeType.contains("pdf") || nomeArquivo.endsWith(".pdf"))
        {
            return ".pdf";
        }

        if (mimeType.contains("png") || nomeArquivo.endsWith(".png"))
        {
            return ".png";
        }

        return ".jpg";
    }

    private String paraJson(Object valor)
    {
        try
        {
            return objectMapper.writeValueAsString(valor);
        }
        catch (JsonProcessingException erro)
        {
            throw new IllegalStateException(erro);
        }
    }

    private <T> T lerJson(String json, TypeReference<T> tipo)
    {
        try
        {
            return objectMapper.readValue(json, tipo);
        }
        catch (JsonProcessingException erro)
        {
            throw new IllegalStateException(erro);
        }
    }
}
