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

    private final Clock clock;

    public DocumentoExemploController(Clock clock) 
    {
        this.clock = clock;
    }

    @GetMapping("/exemplo")
    public ValidacaoRequest exemplo() 
    {
        String emissao = LocalDate.now(clock).minusDays(10).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String texto = """
                CONTA DE ENERGIA
                Empresa: Neoenergia Pernambuco
                Titular: Joao da Silva
                Endereco: Rua das Flores, 150
                Bairro: Boa Vista
                Cidade: Recife - PE
                CEP: 50050-000
                Data de emissão: %s""".formatted(emissao);
        return new ValidacaoRequest("comprovante-residencia.pdf", "application/pdf", texto, null);
    }
}