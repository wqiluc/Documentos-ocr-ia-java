package br.com.cesarschool.documentos.dto;

/** Corpo do POST de cliente. O CPF pode vir com ou sem pontuação ({@code 123.456.789-09} ou {@code 12345678909}). */
public record NovoClienteRequest
(
        String nome,
        String cpf
)
{}
