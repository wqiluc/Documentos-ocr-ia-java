package br.com.cesarschool.documentos.regras;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.cesarschool.documentos.dominio.Cliente;
import br.com.cesarschool.documentos.dominio.ComprovanteResidencia;

/** R02 — nome do titular, endereço, cidade/UF, CEP e data de emissão presentes. */
@Component
@Order(2)
public class RegraCamposObrigatorios implements Regra 
{

    static final String CODIGO = "R02";
    static final String DESCRICAO = "Campos obrigatórios presentes";

    @Override
    public ResultadoRegra avaliar(ComprovanteResidencia c, Cliente cliente) 
    {
        List<String> faltando = new ArrayList<>();
        if (vazio(c.nomeTitular())) 
        {
            faltando.add("nome do titular");
        }

        if (vazio(c.endereco())) 
        {
            faltando.add("endereço");
        }

        if (vazio(c.cidadeUf())) 
        {
            faltando.add("cidade/UF");
        }

        if (vazio(c.cep())) 
        {
            faltando.add("CEP");
        }

        if (c.dataEmissao() == null) 
        {
            faltando.add("data de emissão");
        }

        if (faltando.isEmpty()) 
        {
            return ResultadoRegra.atendida(CODIGO, DESCRICAO);
        }

        return ResultadoRegra.falhou(CODIGO, DESCRICAO,
                "Campo(s) obrigatório(s) não encontrado(s) no comprovante: " + String.join(", ", faltando) + ".");
    }

    private static boolean vazio(String valor) 
    {
        return valor == null || valor.isBlank();
    }
}