package br.com.cesarschool.documentos.dto;

import java.time.LocalDate;

public record DadosComprovanteDto
(
        String nomeTitular,
        String endereco,
        String bairro,
        String cidadeUf,
        String cep,
        String empresaEmissora,
        LocalDate dataEmissao
) 
{}