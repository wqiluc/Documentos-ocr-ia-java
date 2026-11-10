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
import br.com.cesarschool.documentos.dto.NovoClienteRequest;
import br.com.cesarschool.documentos.dto.ValidacaoRequest;
import br.com.cesarschool.documentos.dto.ValidacaoResponse;
import br.com.cesarschool.documentos.repository.ClienteRepository;
import br.com.cesarschool.documentos.service.ValidacaoService;

@RestController
@RequestMapping("/api")
public class ComprovanteController
{

    private static final int TAMANHO_CPF = 11;

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

    /** Cadastra um cliente de teste; ele passa a aparecer no seletor do frontend (que lê GET /api/clientes). */
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody NovoClienteRequest requisicao)
    {
        String nome = requisicao.nome() == null ? "" : requisicao.nome().trim();
        String cpf = requisicao.cpf() == null ? "" : requisicao.cpf().replaceAll("\\D", "");

        if (nome.isEmpty())
        {
            throw new IllegalArgumentException("Informe o nome do cliente.");
        }
        if (cpf.length() != TAMANHO_CPF)
        {
            throw new IllegalArgumentException("O CPF deve ter 11 dígitos.");
        }
        if (clienteRepository.existsByCpf(cpf))
        {
            throw new IllegalArgumentException("Já existe um cliente com o CPF " + cpf + ".");
        }

        Cliente cliente = clienteRepository.save(new Cliente(null, nome, cpf));
        return ResponseEntity.created(URI.create("/api/clientes/" + cliente.getId())).body(cliente);
    }

    @PostMapping("/clientes/{clienteId}/comprovantes")
    public ResponseEntity<ValidacaoResponse> enviarComprovante(@PathVariable Long clienteId,
            @RequestBody ValidacaoRequest requisicao)
    {
        ValidacaoResponse validacao = validacaoService.validarComprovante(clienteId, requisicao);
        URI enderecoDaValidacao = URI.create(
                "/api/clientes/%d/comprovantes/%d".formatted(clienteId, validacao.validacaoId()));

        return ResponseEntity.created(enderecoDaValidacao).body(validacao);
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
