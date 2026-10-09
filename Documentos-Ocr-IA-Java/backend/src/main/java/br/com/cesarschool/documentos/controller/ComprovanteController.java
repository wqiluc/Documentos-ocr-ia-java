package br.com.cesarschool.documentos.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cesarschool.documentos.dominio.Cliente;
import br.com.cesarschool.documentos.dto.ValidacaoRequest;
import br.com.cesarschool.documentos.dto.ValidacaoResponse;
import br.com.cesarschool.documentos.repository.ClienteRepository;
import br.com.cesarschool.documentos.service.ValidacaoService;

@RestController
@RequestMapping("/api")
public class ComprovanteController 
{

    private final ValidacaoService validacaoService;
    private final ClienteRepository clienteRepository;

    public ComprovanteController(ValidacaoService validacaoService, ClienteRepository clienteRepository) 
    {
        this.validacaoService = validacaoService;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/clientes")
    public List<Cliente> listarClientes() 
    {
        return clienteRepository.findAll();
    }

    @PostMapping("/clientes/{clienteId}/comprovantes")
    public ResponseEntity<ValidacaoResponse> enviarComprovante(@PathVariable Long clienteId,
            @RequestBody ValidacaoRequest request) 
    {
        ValidacaoResponse resposta = validacaoService.validarComprovante(clienteId, request);
        URI local = URI.create("/api/clientes/%d/comprovantes/%d".formatted(clienteId, resposta.validacaoId()));
        return ResponseEntity.created(local).body(resposta);
    }

    @GetMapping("/clientes/{clienteId}/comprovantes/{validacaoId}")
    public ValidacaoResponse consultar(@PathVariable Long clienteId, @PathVariable Long validacaoId) 
    {
        return validacaoService.buscar(clienteId, validacaoId);
    }

    @GetMapping("/validacoes")
    public List<ValidacaoResponse> listarValidacoes() 
    {
        return validacaoService.listar();
    }
}