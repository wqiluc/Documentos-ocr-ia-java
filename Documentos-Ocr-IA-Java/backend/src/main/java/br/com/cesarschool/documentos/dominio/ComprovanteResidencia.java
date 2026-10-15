package br.com.cesarschool.documentos.dominio;

import java.time.LocalDate;

/** Dados do comprovante após OCR + IA, mais as métricas do OCR usadas na regra de legibilidade. */
public record ComprovanteResidencia
(
        String nomeTitular,
        String endereco,
        String bairro,
        String cidadeUf,
        String cep,
        String empresaEmissora,
        LocalDate dataEmissao,
        String textoOcr,
        double confiancaOcr
) 
{}