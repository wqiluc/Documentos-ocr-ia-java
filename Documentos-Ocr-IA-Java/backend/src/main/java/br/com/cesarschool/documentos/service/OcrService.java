package br.com.cesarschool.documentos.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class OcrService
{

    private static final Logger log = LoggerFactory.getLogger(OcrService.class);

    /** Confiança atribuída a um texto que já veio digitado/extraído (não passou por OCR). */
    static final double CONFIANCA_TEXTO_ENVIADO = 95.0;

    // Colunas do TSV do Tesseract:
    // level, page_num, block_num, par_num, line_num, word_num, left, top, width, height, conf, text
    private static final int TOTAL_DE_COLUNAS_TSV = 12;
    private static final int COLUNA_NIVEL = 0;
    private static final int COLUNA_PAGINA = 1;
    private static final int COLUNA_BLOCO = 2;
    private static final int COLUNA_PARAGRAFO = 3;
    private static final int COLUNA_LINHA = 4;
    private static final int COLUNA_CONFIANCA = 10;
    private static final int COLUNA_TEXTO = 11;
    private static final String NIVEL_PALAVRA = "5";

    public record ResultadoOcr(String texto, double confianca, String descricao)
    {}

    private final AppProperties propriedades;

    public OcrService(AppProperties propriedades)
    {
        this.propriedades = propriedades;
    }

    public ResultadoOcr extrair(ImagemTratada imagem, String textoEnviado)
    {
        boolean recebeuTexto = textoEnviado != null && !textoEnviado.isBlank();
        String motivoDoFallback = "Nenhuma imagem enviada";

        if (imagem != null && imagem.isPdf())
        {
            motivoDoFallback = "PDF precisa do OpenCV para virar imagem";
        }
        else if (imagem != null)
        {
            try
            {
                ResultadoOcr resultadoTesseract = executarTesseract(imagem);

                if (!resultadoTesseract.texto().isBlank() || !recebeuTexto)
                {
                    return resultadoTesseract;
                }

                motivoDoFallback = "Tesseract não reconheceu texto ❌";
            }
            catch (Exception erro)
            {
                log.warn("Tesseract indisponível: {}", erro.getMessage());
                motivoDoFallback = "Tesseract indisponível";
            }
        }

        if (recebeuTexto)
        {
            return new ResultadoOcr(textoEnviado, CONFIANCA_TEXTO_ENVIADO,
                    "Texto recebido em conteudoDocumento (" + motivoDoFallback + ")");
        }

        return new ResultadoOcr("", 0, "Nenhum texto extraído (" + motivoDoFallback + ")");
    }

    private ResultadoOcr executarTesseract(ImagemTratada imagem) throws IOException, InterruptedException
    {
        AppProperties.Ocr configuracaoOcr = propriedades.ocr();
        Path arquivoTemporario = Files.createTempFile("ocr-", imagem.extensao());

        try
        {
            Files.write(arquivoTemporario, imagem.conteudo());

            List<String> comando = new ArrayList<>(List.of(
                    configuracaoOcr.command(), arquivoTemporario.toString(), "stdout",
                    "-l", configuracaoOcr.language()));

            String diretorioTessdata = configuracaoOcr.tessdataPath();
            if (diretorioTessdata != null && !diretorioTessdata.isBlank()
                    && Files.isDirectory(Path.of(diretorioTessdata)))
            {
                comando.add("--tessdata-dir");
                comando.add(diretorioTessdata);
            }

            comando.add("tsv");

            ResultadoOcr textoLido = lerTsv(ComandoExterno.executar(comando));
            return new ResultadoOcr(textoLido.texto(), textoLido.confianca(),
                    "Texto extraído (confiança %.0f%%)".formatted(textoLido.confianca()));
        }
        finally
        {
            Files.deleteIfExists(arquivoTemporario);
        }
    }

    /** Monta o texto linha a linha e calcula a confiança média das palavras. */
    static ResultadoOcr lerTsv(String tsv)
    {
        Map<String, StringBuilder> palavrasPorLinha = new LinkedHashMap<>();
        double somaDasConfiancas = 0;
        int quantidadeDePalavras = 0;

        for (String linhaTsv : tsv.split("\\R"))
        {
            String[] colunas = linhaTsv.split("\t", -1);

            if (colunas.length < TOTAL_DE_COLUNAS_TSV || !NIVEL_PALAVRA.equals(colunas[COLUNA_NIVEL]))
            {
                continue;
            }

            String palavra = colunas[COLUNA_TEXTO].strip();
            double confiancaDaPalavra = Double.parseDouble(colunas[COLUNA_CONFIANCA]);

            if (palavra.isEmpty() || confiancaDaPalavra < 0)
            {
                continue;
            }

            String identificadorDaLinha = colunas[COLUNA_PAGINA] + "-" + colunas[COLUNA_BLOCO] + "-"
                    + colunas[COLUNA_PARAGRAFO] + "-" + colunas[COLUNA_LINHA];

            palavrasPorLinha.computeIfAbsent(identificadorDaLinha, chave -> new StringBuilder())
                    .append(palavra)
                    .append(' ');

            somaDasConfiancas += confiancaDaPalavra;
            quantidadeDePalavras++;
        }

        String texto = palavrasPorLinha.values().stream()
                .map(linha -> linha.toString().strip())
                .collect(Collectors.joining("\n"));

        double confiancaMedia = quantidadeDePalavras == 0 ? 0 : somaDasConfiancas / quantidadeDePalavras;
        return new ResultadoOcr(texto, confiancaMedia, null);
    }
}
