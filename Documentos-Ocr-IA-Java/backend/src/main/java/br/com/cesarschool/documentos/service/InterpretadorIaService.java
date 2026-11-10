package br.com.cesarschool.documentos.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cesarschool.documentos.config.AppProperties;
import br.com.cesarschool.documentos.dto.DadosComprovanteDto;

/**
 * Etapa 4 — transforma o texto do OCR em JSON estruturado do comprovante.
 * Com OPENAI_API_KEY usa a OpenAI; sem chave (ou se a chamada falhar) usa um interpretador local por regex.
 */
@Service
public class InterpretadorIaService
{

    private static final Logger log = LoggerFactory.getLogger(InterpretadorIaService.class);

    private static final String PROMPT = """
            Você extrai dados de comprovantes de residência brasileiros (contas de luz, água, internet).
            O texto vem de OCR e pode ter erros. Responda APENAS com um JSON com as chaves:
            nomeTitular, endereco (logradouro e número), bairro, cidadeUf (ex.: "Recife - PE"),
            cep (formato 00000-000), empresaEmissora, dataEmissao (formato yyyy-MM-dd).
            Use null para o que não encontrar. Não invente dados.""";

    // ---------- padrões do interpretador local (regex) ----------

    private static final Pattern PADRAO_TITULAR = campoRotulado("(?:titular|nome do cliente|cliente|nome)");
    private static final Pattern PADRAO_ENDERECO = campoRotulado("(?:endere[cç]o|logradouro)");
    private static final Pattern PADRAO_BAIRRO = campoRotulado("bairro");
    private static final Pattern PADRAO_CIDADE = campoRotulado("(?:cidade(?:\\s*/\\s*uf)?|munic[ií]pio)");
    private static final Pattern PADRAO_EMPRESA = campoRotulado(
            "(?:empresa(?: emissora)?|emissora|distribuidora|concession[aá]ria)");
    private static final Pattern PADRAO_CEP_ROTULADO = campoRotulado("cep");
    private static final Pattern PADRAO_CEP = Pattern.compile("\\b(\\d{5})\\s*-?\\s*(\\d{3})\\b");
    private static final Pattern PADRAO_DATA_EMISSAO = Pattern.compile(
            "(?im)(?:data\\s+(?:de\\s+)?emiss[aã]o|emitid[oa]\\s+em|emiss[aã]o)\\s*[:\\-]?\\s*(\\d{2})[/.-](\\d{2})[/.-](\\d{4})");

    private static final DateTimeFormatter FORMATO_DATA_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public record Interpretacao(DadosComprovanteDto dados, String descricao)
    {}

    private final AppProperties propriedades;
    private final ObjectMapper objectMapper;
    private final RestClient restClient;

    public InterpretadorIaService(AppProperties propriedades, ObjectMapper objectMapper, RestClient.Builder restClientBuilder)
    {
        this.propriedades = propriedades;
        this.objectMapper = objectMapper;
        this.restClient = restClientBuilder.build();
    }

    public Interpretacao interpretar(String textoOcr)
    {
        if (textoOcr == null || textoOcr.isBlank())
        {
            return new Interpretacao(dadosVazios(), "Sem texto para interpretar");
        }

        AppProperties.OpenAi configuracaoOpenAi = propriedades.ai().openai();

        if (!configuracaoOpenAi.configurada())
        {
            return new Interpretacao(interpretarLocal(textoOcr), "JSON estruturado gerado (interpretador local — regex)");
        }

        try
        {
            return new Interpretacao(chamarOpenAi(textoOcr, configuracaoOpenAi),
                    "JSON estruturado gerado (OpenAI " + configuracaoOpenAi.model() + ")");
        }
        catch (Exception erro)
        {
            log.warn("Falha na OpenAI, usando interpretador local: {}", erro.getMessage());
            return new Interpretacao(interpretarLocal(textoOcr),
                    "JSON estruturado gerado (interpretador local — OpenAI falhou)");
        }
    }

    private DadosComprovanteDto chamarOpenAi(String textoOcr, AppProperties.OpenAi configuracaoOpenAi)
            throws JsonProcessingException
    {
        Map<String, Object> corpoDaRequisicao = Map.of(
                "model", configuracaoOpenAi.model(),
                "temperature", 0,
                "response_format", Map.of("type", "json_object"),
                "messages", List.of(
                        Map.of("role", "system", "content", PROMPT),
                        Map.of("role", "user", "content", textoOcr)));

        JsonNode respostaDaOpenAi = restClient.post()
                .uri(configuracaoOpenAi.url())
                .header("Authorization", "Bearer " + configuracaoOpenAi.apiKey())
                .contentType(MediaType.APPLICATION_JSON)
                .body(corpoDaRequisicao)
                .retrieve()
                .body(JsonNode.class);

        String jsonDoComprovante = respostaDaOpenAi.path("choices").path(0).path("message").path("content").asText();
        return objectMapper.readValue(jsonDoComprovante, DadosComprovanteDto.class);
    }

    // ---------- interpretador local (regex) ----------

    DadosComprovanteDto interpretarLocal(String textoOcr)
    {
        return new DadosComprovanteDto(
                extrairValor(PADRAO_TITULAR, textoOcr),
                extrairValor(PADRAO_ENDERECO, textoOcr),
                extrairValor(PADRAO_BAIRRO, textoOcr),
                extrairValor(PADRAO_CIDADE, textoOcr),
                extrairCep(textoOcr),
                extrairValor(PADRAO_EMPRESA, textoOcr),
                extrairDataEmissao(textoOcr));
    }

    /** Monta um padrão para linhas no formato "Rótulo: valor", capturando o valor. */
    private static Pattern campoRotulado(String rotulo)
    {
        return Pattern.compile("(?im)^\\s*" + rotulo + "\\s*:\\s*(.+?)\\s*$");
    }

    private static String extrairValor(Pattern padrao, String textoOcr)
    {
        Matcher correspondencia = padrao.matcher(textoOcr);
        return correspondencia.find() ? correspondencia.group(1).strip() : null;
    }

    /** Procura o CEP primeiro na linha "CEP: ..."; sem ela, no texto inteiro. Normaliza para 00000-000. */
    private static String extrairCep(String textoOcr)
    {
        String cepRotulado = extrairValor(PADRAO_CEP_ROTULADO, textoOcr);
        Matcher correspondencia = PADRAO_CEP.matcher(cepRotulado != null ? cepRotulado : textoOcr);

        return correspondencia.find()
                ? correspondencia.group(1) + "-" + correspondencia.group(2)
                : cepRotulado;
    }

    private static LocalDate extrairDataEmissao(String textoOcr)
    {
        Matcher correspondencia = PADRAO_DATA_EMISSAO.matcher(textoOcr);

        if (!correspondencia.find())
        {
            return null;
        }

        String dia = correspondencia.group(1);
        String mes = correspondencia.group(2);
        String ano = correspondencia.group(3);

        try
        {
            return LocalDate.parse(dia + "/" + mes + "/" + ano, FORMATO_DATA_BR);
        }
        catch (DateTimeParseException erro)
        {
            return null;
        }
    }

    private static DadosComprovanteDto dadosVazios()
    {
        return new DadosComprovanteDto(null, null, null, null, null, null, null);
    }
}
