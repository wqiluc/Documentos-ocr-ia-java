package br.com.cesarschool.documentos.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.cesarschool.documentos.config.AppProperties;
import br.com.cesarschool.documentos.service.OpenCvService.ImagemTratada;

/**
 * Etapa 3 — extrai o texto com o Tesseract (saída TSV, para obter também a confiança média).
 * Sem imagem ou sem Tesseract, usa o texto enviado em {@code conteudoDocumento}.
 */
@Service
public class OcrService {

    private static final Logger log = LoggerFactory.getLogger(OcrService.class);

    /** Confiança atribuída a um texto que já veio digitado/extraído (não passou por OCR). */
    static final double CONFIANCA_TEXTO_ENVIADO = 95.0;

    public record ResultadoOcr(String texto, double confianca, String descricao) {}

    private final AppProperties props;

    public OcrService(AppProperties props) {
        this.props = props;
    }

    public ResultadoOcr extrair(ImagemTratada imagem, String textoEnviado) {
        String motivoFallback = "nenhuma imagem enviada";
        if (imagem != null && imagem.isPdf()) {
            motivoFallback = "PDF precisa do OpenCV para virar imagem";
        } else if (imagem != null) {
            try {
                ResultadoOcr ocr = executarTesseract(imagem);
                if (!ocr.texto().isBlank() || textoEnviado == null || textoEnviado.isBlank()) {
                    return ocr;
                }
                motivoFallback = "Tesseract não reconheceu texto";
            } catch (Exception e) {
                log.warn("Tesseract indisponível: {}", e.getMessage());
                motivoFallback = "Tesseract indisponível";
            }
        }
        if (textoEnviado != null && !textoEnviado.isBlank()) {
            return new ResultadoOcr(textoEnviado, CONFIANCA_TEXTO_ENVIADO,
                    "Texto recebido em conteudoDocumento (" + motivoFallback + ")");
        }
        return new ResultadoOcr("", 0, "Nenhum texto extraído (" + motivoFallback + ")");
    }

    private ResultadoOcr executarTesseract(ImagemTratada imagem) throws Exception {
        Path arquivo = Files.createTempFile("ocr-", imagem.extensao());
        try {
            Files.write(arquivo, imagem.conteudo());
            List<String> comando = new ArrayList<>(List.of(props.ocr().command(), arquivo.toString(), "stdout",
                    "-l", props.ocr().language()));
            if (props.ocr().tessdataPath() != null && !props.ocr().tessdataPath().isBlank()
                    && Files.isDirectory(Path.of(props.ocr().tessdataPath()))) {
                comando.add("--tessdata-dir");
                comando.add(props.ocr().tessdataPath());
            }
            comando.add("tsv");
            ResultadoOcr resultado = lerTsv(ComandoExterno.executar(comando));
            return new ResultadoOcr(resultado.texto(), resultado.confianca(),
                    "Texto extraído (confiança %.0f%%)".formatted(resultado.confianca()));
        } finally {
            Files.deleteIfExists(arquivo);
        }
    }

    /** Monta o texto linha a linha e calcula a confiança média das palavras. */
    static ResultadoOcr lerTsv(String tsv) {
        Map<String, StringBuilder> linhas = new LinkedHashMap<>();
        double somaConfianca = 0;
        int palavras = 0;
        for (String linha : tsv.split("\\R")) {
            String[] col = linha.split("\t", -1);
            if (col.length < 12 || !"5".equals(col[0])) continue; // nível 5 = palavra
            String palavra = col[11].strip();
            double conf = Double.parseDouble(col[10]);
            if (palavra.isEmpty() || conf < 0) continue;
            String chave = col[1] + "-" + col[2] + "-" + col[3] + "-" + col[4];
            linhas.computeIfAbsent(chave, k -> new StringBuilder()).append(palavra).append(' ');
            somaConfianca += conf;
            palavras++;
        }
        String texto = String.join("\n", linhas.values().stream().map(sb -> sb.toString().strip()).toList());
        return new ResultadoOcr(texto, palavras == 0 ? 0 : somaConfianca / palavras, null);
    }
}
