package br.com.cesarschool.documentos.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cesarschool.documentos.config.AppProperties;
import br.com.cesarschool.documentos.dto.DadosComprovanteDto;

/**
 * Etapa 4 — transforma o texto do OCR em JSON estruturado do comprovante.
 * Com OPENAI_API_KEY usa a OpenAI; sem chave (ou se a chamada falhar) usa um interpretador local por regex.
 */
@Service
public class InterpretadorIaService {

    private static final Logger log = LoggerFactory.getLogger(InterpretadorIaService.class);

    private static final String PROMPT = """
            Você extrai dados de comprovantes de residência brasileiros (contas de luz, água, internet).
            O texto vem de OCR e pode ter erros. Responda APENAS com um JSON com as chaves:
            nomeTitular, endereco (logradouro e número), bairro, cidadeUf (ex.: "Recife - PE"),
            cep (formato 00000-000), empresaEmissora, dataEmissao (formato yyyy-MM-dd).
            Use null para o que não encontrar. Não invente dados.""";

    public record Interpretacao(DadosComprovanteDto dados, String descricao) {}

    private final AppProperties props;
    private final ObjectMapper objectMapper;
    private final RestClient restClient;

    public InterpretadorIaService(AppProperties props, ObjectMapper objectMapper, RestClient.Builder builder) {
        this.props = props;
        this.objectMapper = objectMapper;
        this.restClient = builder.build();
    }

    public Interpretacao interpretar(String texto) {
        if (texto == null || texto.isBlank()) {
            return new Interpretacao(vazio(), "Sem texto para interpretar");
        }
        AppProperties.OpenAi openai = props.ai().openai();
        if (openai.configurada()) {
            try {
                return new Interpretacao(chamarOpenAi(texto, openai),
                        "JSON estruturado gerado (OpenAI " + openai.model() + ")");
            } catch (Exception e) {
                log.warn("Falha na OpenAI, usando interpretador local: {}", e.getMessage());
                return new Interpretacao(interpretarLocal(texto),
                        "JSON estruturado gerado (interpretador local — OpenAI falhou)");
            }
        }
        return new Interpretacao(interpretarLocal(texto), "JSON estruturado gerado (interpretador local — regex)");
    }

    private DadosComprovanteDto chamarOpenAi(String texto, AppProperties.OpenAi openai) throws Exception {
        Map<String, Object> corpo = Map.of(
                "model", openai.model(),
                "temperature", 0,
                "response_format", Map.of("type", "json_object"),
                "messages", List.of(
                        Map.of("role", "system", "content", PROMPT),
                        Map.of("role", "user", "content", texto)));

        JsonNode resposta = restClient.post()
                .uri(openai.url())
                .header("Authorization", "Bearer " + openai.apiKey())
                .contentType(MediaType.APPLICATION_JSON)
                .body(corpo)
                .retrieve()
                .body(JsonNode.class);

        String json = resposta.path("choices").path(0).path("message").path("content").asText();
        return objectMapper.readValue(json, DadosComprovanteDto.class);
    }

    // ---------- interpretador local (regex) ----------

    private static final Pattern TITULAR = rotulo("(?:titular|nome do cliente|cliente|nome)");
    private static final Pattern ENDERECO = rotulo("(?:endere[cç]o|logradouro)");
    private static final Pattern BAIRRO = rotulo("bairro");
    private static final Pattern CIDADE = rotulo("(?:cidade(?:\\s*/\\s*uf)?|munic[ií]pio)");
    private static final Pattern EMPRESA = rotulo("(?:empresa(?: emissora)?|emissora|distribuidora|concession[aá]ria)");
    private static final Pattern CEP = Pattern.compile("\\b(\\d{5})\\s*-?\\s*(\\d{3})\\b");
    private static final Pattern CEP_ROTULADO = rotulo("cep");
    private static final Pattern EMISSAO = Pattern.compile(
            "(?im)(?:data\\s+(?:de\\s+)?emiss[aã]o|emitid[oa]\\s+em|emiss[aã]o)\\s*[:\\-]?\\s*(\\d{2})[/.-](\\d{2})[/.-](\\d{4})");

    private static Pattern rotulo(String nome) {
        return Pattern.compile("(?im)^\\s*" + nome + "\\s*:\\s*(.+?)\\s*$");
    }

    DadosComprovanteDto interpretarLocal(String texto) {
        String cep = primeiro(CEP_ROTULADO, texto);
        Matcher mCep = CEP.matcher(cep != null ? cep : texto);
        cep = mCep.find() ? mCep.group(1) + "-" + mCep.group(2) : cep;

        return new DadosComprovanteDto(
                primeiro(TITULAR, texto),
                primeiro(ENDERECO, texto),
                primeiro(BAIRRO, texto),
                primeiro(CIDADE, texto),
                cep,
                primeiro(EMPRESA, texto),
                dataEmissao(texto));
    }

    private static String primeiro(Pattern padrao, String texto) {
        Matcher m = padrao.matcher(texto);
        return m.find() ? m.group(1).strip() : null;
    }

    private static LocalDate dataEmissao(String texto) {
        Matcher m = EMISSAO.matcher(texto);
        if (!m.find()) return null;
        try {
            return LocalDate.parse(m.group(1) + "/" + m.group(2) + "/" + m.group(3),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            return null;
        }
    }

    private static DadosComprovanteDto vazio() {
        return new DadosComprovanteDto(null, null, null, null, null, null, null);
    }
}
