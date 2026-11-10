package br.com.cesarschool.documentos.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.cesarschool.documentos.config.AppProperties;

/**
 * Etapa 2 — pré-processa a imagem com o script OpenCV (scripts/pre-processar_documento.py).
 * Se o script não estiver disponível, segue com a imagem original.
 */
@Service
public class OpenCvService
{

    private static final Logger log = LoggerFactory.getLogger(OpenCvService.class);

    private static final String EXTENSAO_IMAGEM_TRATADA = ".png";

    public record ImagemTratada(byte[] conteudo, String extensao, boolean tratada, String descricao)
    {
        public boolean isPdf()
        {
            return ".pdf".equals(extensao);
        }
    }

    private final AppProperties propriedades;

    public OpenCvService(AppProperties propriedades)
    {
        this.propriedades = propriedades;
    }

    public ImagemTratada tratar(byte[] conteudoOriginal, String extensao)
    {
        String comandoOpenCv = propriedades.opencv().command();

        if (comandoOpenCv == null || comandoOpenCv.isBlank())
        {
            return new ImagemTratada(conteudoOriginal, extensao, false, "OpenCV não configurado — imagem original");
        }

        Path arquivoEntrada = null;
        Path arquivoSaida = null;

        try
        {
            arquivoEntrada = Files.createTempFile("documento-", extensao);
            arquivoSaida = Files.createTempFile("documento-tratado-", EXTENSAO_IMAGEM_TRATADA);
            Files.write(arquivoEntrada, conteudoOriginal);

            List<String> comandoCompleto = ComandoExterno.separarArgumentos(comandoOpenCv);
            comandoCompleto.add(arquivoEntrada.toString());
            comandoCompleto.add(arquivoSaida.toString());
            ComandoExterno.executar(comandoCompleto);

            return new ImagemTratada(Files.readAllBytes(arquivoSaida), EXTENSAO_IMAGEM_TRATADA, true,
                    "Imagem tratada (cinza, ruído, binarização, inclinação)");
        }
        catch (Exception erro)
        {
            log.warn("OpenCV indisponível, seguindo com a imagem original: {}", erro.getMessage());
            return new ImagemTratada(conteudoOriginal, extensao, false, "OpenCV indisponível — imagem original");
        }
        finally
        {
            apagarArquivoTemporario(arquivoEntrada);
            apagarArquivoTemporario(arquivoSaida);
        }
    }

    private static void apagarArquivoTemporario(Path arquivo)
    {
        if (arquivo == null)
        {
            return;
        }

        try
        {
            Files.deleteIfExists(arquivo);
        }
        catch (Exception ignorado)
        {
            // Falhar ao apagar um arquivo temporário não deve interromper a validação.
        }
    }
}
