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
    public ResultadoRegra avaliar(ComprovanteResidencia comprovante, Cliente cliente)
    {
        List<String> camposAusentes = new ArrayList<>();

        if (estaVazio(comprovante.nomeTitular()))
        {
            camposAusentes.add("nome do titular");
        }

        if (estaVazio(comprovante.endereco()))
        {
            camposAusentes.add("endereço");
        }

        if (estaVazio(comprovante.cidadeUf()))
        {
            camposAusentes.add("cidade/UF");
        }

        if (estaVazio(comprovante.cep()))
        {
            camposAusentes.add("CEP");
        }

        if (comprovante.dataEmissao() == null)
        {
            camposAusentes.add("data de emissão");
        }

        if (camposAusentes.isEmpty())
        {
            return ResultadoRegra.atendida(CODIGO, DESCRICAO);
        }

        return ResultadoRegra.falhou(CODIGO, DESCRICAO,
                "Campo(s) obrigatório(s) não encontrado(s) no comprovante: " + String.join(", ", camposAusentes) + ".");
    }

    private static boolean estaVazio(String valor)
    {
        return valor == null || valor.isBlank();
    }
}
