package br.com.cesarschool.documentos.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

final class ComandoExterno 
{

    private static final long TIMEOUT_SEGUNDOS = 60;

    private ComandoExterno() 
    {

    }

    static List<String> dividir(String comando) 
    {
        return new ArrayList<>(List.of(comando.strip().split("\\s+")));
    }

    static String executar(List<String> comando) throws IOException, InterruptedException 
    {
        Process processo = new ProcessBuilder(comando).redirectErrorStream(false).start();
        byte[] saida = processo.getInputStream().readAllBytes();
        byte[] erro = processo.getErrorStream().readAllBytes();

        if (!processo.waitFor(TIMEOUT_SEGUNDOS, TimeUnit.SECONDS)) 
        {
            processo.destroyForcibly();
            throw new IOException("Tempo esgotado executando " + comando.get(0));
        }

        if (processo.exitValue() != 0) 
        {
            throw new IOException(comando.get(0) + " terminou com código " + processo.exitValue() + ": "
                    + new String(erro, StandardCharsets.UTF_8).strip());
        }
        return new String(saida, StandardCharsets.UTF_8);
    }
}