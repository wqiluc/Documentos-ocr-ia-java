package br.com.cesarschool.documentos.regras;

import java.text.Normalizer;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.cesarschool.documentos.dominio.Cliente;
import br.com.cesarschool.documentos.dominio.ComprovanteResidencia;

/**
 * R08 — nome do titular do comprovante igual ao do cliente cadastrado (ignorando acentos, caixa e espaços).
 * Se for diferente, vai para ANALISE_MANUAL, pois o comprovante pode estar no nome de um familiar.
 */
@Component
@Order(5)
public class RegraTitularIgualCliente implements Regra 
{

    static final String CODIGO = "R08";
    static final String DESCRICAO = "Titular igual ao cliente cadastrado";

    @Override
    public ResultadoRegra avaliar(ComprovanteResidencia comprovante, Cliente cliente) 
    {
        String titular = comprovante.nomeTitular();
        if (titular == null || titular.isBlank()) 
        {
            return ResultadoRegra.ignorada(CODIGO, DESCRICAO); // já reportado na R02
        }

        if (normalizar(titular).equals(normalizar(cliente.getNome()))) 
        {
            return ResultadoRegra.atendida(CODIGO, DESCRICAO);
        }

        return ResultadoRegra.analiseManual(CODIGO, DESCRICAO,
                "Comprovante em nome de \"%s\", diferente do cliente \"%s\". Encaminhado para análise manual."
                        .formatted(titular.strip(), cliente.getNome()));
    }

    static String normalizar(String nome) 
    {
        return Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase()
                .replaceAll("[^a-z ]", " ")
                .replaceAll("\\s+", " ")
                .strip();
    }
}