package br.com.cesarschool.documentos.controller;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cesarschool.documentos.dto.ValidacaoRequest;

/** Payload pronto para a demo (data de emissão sempre recente, para ser APROVADO para o cliente 1). */
@RestController
@RequestMapping("/api/documentos")
public class DocumentoExemploController
{

    private static final int DIAS_DESDE_A_EMISSAO = 10;
    private static final DateTimeFormatter FORMATO_DATA_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Clock clock;

    public DocumentoExemploController(Clock clock)
    {
        this.clock = clock;
    }

    @GetMapping("/exemplo")
    public ValidacaoRequest exemplo()
    {
        String dataEmissao = LocalDate.now(clock).minusDays(DIAS_DESDE_A_EMISSAO).format(FORMATO_DATA_BR);
        String textoDoComprovante = """
                CONTA DE ENERGIA
                Empresa: Neoenergia Pernambuco
                Titular: Joao da Silva
                Endereco: Rua das Flores, 150
                Bairro: Boa Vista
                Cidade: Recife - PE
                CEP: 50050-000
                Data de emissão: %s""".formatted(dataEmissao);

        return new ValidacaoRequest("comprovante-residencia.pdf", "application/pdf", textoDoComprovante, null);
    }
}
