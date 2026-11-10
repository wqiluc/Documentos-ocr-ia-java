package br.com.cesarschool.documentos.exception;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Converte as exceções da API em respostas no formato {"erro": "mensagem"}. */
@RestControllerAdvice
public class GlobalExceptionHandler
{

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> clienteNaoEncontrado(ClienteNaoEncontradoException excecao)
    {
        return respostaDeErro(HttpStatus.NOT_FOUND, excecao.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> naoEncontrado(NoSuchElementException excecao)
    {
        return respostaDeErro(HttpStatus.NOT_FOUND, excecao.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Map<String, String>> requisicaoInvalida(Exception excecao)
    {
        return respostaDeErro(HttpStatus.BAD_REQUEST, excecao.getMessage());
    }

    private ResponseEntity<Map<String, String>> respostaDeErro(HttpStatus status, String mensagem)
    {
        String mensagemDeErro = mensagem == null ? status.getReasonPhrase() : mensagem;
        return ResponseEntity.status(status).body(Map.of("erro", mensagemDeErro));
    }
}
