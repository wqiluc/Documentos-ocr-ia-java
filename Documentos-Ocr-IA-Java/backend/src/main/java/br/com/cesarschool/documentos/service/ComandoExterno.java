package br.com.cesarschool.documentos.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/** Executa programas externos (Tesseract, script do OpenCV) e devolve a saída padrão. */
final class ComandoExterno
{

    private static final long TEMPO_LIMITE_EM_SEGUNDOS = 60;

    private ComandoExterno()
    {}

    /** Separa um comando configurado (ex.: "python3 scripts/x.py") em argumentos. A lista devolvida é mutável. */
    static List<String> separarArgumentos(String comando)
    {
        return new ArrayList<>(List.of(comando.strip().split("\\s+")));
    }

    static String executar(List<String> comando) throws IOException, InterruptedException
    {
        String programa = comando.get(0);
        Process processo = new ProcessBuilder(comando).start();

        byte[] saidaPadrao = processo.getInputStream().readAllBytes();
        byte[] saidaDeErro = processo.getErrorStream().readAllBytes();

        if (!processo.waitFor(TEMPO_LIMITE_EM_SEGUNDOS, TimeUnit.SECONDS))
        {
            processo.destroyForcibly();
            throw new IOException("Tempo esgotado executando " + programa);
        }

        if (processo.exitValue() != 0)
        {
            throw new IOException(programa + " terminou com código " + processo.exitValue() + ": "
                    + new String(saidaDeErro, StandardCharsets.UTF_8).strip());
        }

        return new String(saidaPadrao, StandardCharsets.UTF_8);
    }
}
