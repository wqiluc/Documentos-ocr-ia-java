package br.com.cesarschool.documentos.exception;

public class ClienteNaoEncontradoException extends RuntimeException 
{

    public ClienteNaoEncontradoException(Long clienteId) 
    {
        super("Cliente " + clienteId + " não encontrado.");
    }
}
