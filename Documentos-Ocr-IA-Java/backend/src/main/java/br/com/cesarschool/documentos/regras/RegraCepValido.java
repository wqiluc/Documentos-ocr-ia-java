package br.com.cesarschool.documentos.regras;

import java.util.regex.Pattern;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.cesarschool.documentos.dominio.Cliente;
import br.com.cesarschool.documentos.dominio.ComprovanteResidencia;

/** R07 — CEP com formato válido (8 dígitos, com ou sem hífen). */
@Component
@Order(4)
public class RegraCepValido implements Regra 
{

    static final String CODIGO = "R07";
    static final String DESCRICAO = "CEP válido";

    private static final Pattern CEP = Pattern.compile("^\\d{5}-?\\d{3}$");

    @Override
    public ResultadoRegra avaliar(ComprovanteResidencia comprovante, Cliente cliente) {
        String cep = comprovante.cep();
        if (cep == null || cep.isBlank()) 
        {
            return ResultadoRegra.ignorada(CODIGO, DESCRICAO); // já reportado na R02
        }

        String limpo = cep.strip();

        if (!CEP.matcher(limpo).matches() || limpo.replace("-", "").chars().distinct().count() == 1) 
        {
            return ResultadoRegra.falhou(CODIGO, DESCRICAO,
                    "CEP inválido (\"%s\"). O CEP deve ter 8 dígitos, ex.: 50050-000.".formatted(cep));
        }
        return ResultadoRegra.atendida(CODIGO, DESCRICAO);
    }
}