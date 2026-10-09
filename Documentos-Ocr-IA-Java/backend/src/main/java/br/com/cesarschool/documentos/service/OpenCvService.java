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

    public record ImagemTratada(byte[] conteudo, String extensao, boolean tratada, String descricao) 
    {
        public boolean isPdf() 
        {
            return ".pdf".equals(extensao);
        }
    }

    private final AppProperties props;

    public OpenCvService(AppProperties props) 
    {
        this.props = props;
    }

    public ImagemTratada tratar(byte[] original, String extensao) 
    {
        String comando = props.opencv().command();
        if (comando == null || comando.isBlank()) 
        {
            return new ImagemTratada(original, extensao, false, "OpenCV não configurado — imagem original");
        }
        Path entrada = null;
        Path saida = null;
        try 
        {
            entrada = Files.createTempFile("documento-", extensao);
            saida = Files.createTempFile("documento-tratado-", ".png");
            Files.write(entrada, original);

            List<String> partes = ComandoExterno.dividir(comando);
            partes.add(entrada.toString());
            partes.add(saida.toString());
            ComandoExterno.executar(partes);

            return new ImagemTratada(Files.readAllBytes(saida), ".png", true,
                    "Imagem tratada (cinza, ruído, binarização, inclinação)");
        } 
        catch (Exception e) 
        {
            log.warn("OpenCV indisponível, seguindo com a imagem original: {}", e.getMessage());
            return new ImagemTratada(original, extensao, false, "OpenCV indisponível — imagem original");
        } 
        finally 
        {
            apagar(entrada);
            apagar(saida);
        }
    }

    private static void apagar(Path arquivo) 
    {
        try 
        {
            if (arquivo != null) 
            {
                Files.deleteIfExists(arquivo);
            }
        } 
        catch (Exception ignorado) 
        {
            // arquivo temporário
        }
    }
}