package br.com.cesarschool.documentos.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.cesarschool.documentos.dominio.StatusValidacao;

public record ValidacaoResponse
(
        Long validacaoId,
        Long clienteId,
        String nomeCliente,
        String nomeArquivo,
        LocalDateTime criadoEm,
        List<EtapaPipeline> etapas,
        DadosComprovanteDto dadosExtraidos,
        Resultado resultado
)

{

    public record Resultado(StatusValidacao status, List<String> regrasAtendidas, List<String> motivos) 
    {}
}