package br.com.cesarschool.documentos.regras;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import br.com.cesarschool.documentos.dominio.Cliente;
import br.com.cesarschool.documentos.dominio.ComprovanteResidencia;

class RegraCepValidoTest 
{

    private final RegraCepValido regra = new RegraCepValido();
    private final Cliente cliente = new Cliente(1L, "Joao da Silva", "52998224725");

    @ParameterizedTest
    
    @ValueSource(strings = {"50050-000", "50050000", " 01310-100 "})
    void aceitaCepComOitoDigitos(String cep) 
    {
        assertThat(regra.avaliar(comprovante(cep), cliente).isAtendida()).isTrue();
    }

    @ParameterizedTest

    @ValueSource(strings = {"5005-000", "500500001", "ABCDE-123", "00000-000", "50.050-000"})
    void rejeitaCepInvalido(String cep) 
    {
        assertThat(regra.avaliar(comprovante(cep), cliente).isFalhou()).isTrue();
    }

    private static ComprovanteResidencia comprovante(String cep) 
    {
        return new ComprovanteResidencia("Joao da Silva", "Rua A, 1", "Centro", "Recife - PE", cep,
                "Neoenergia", LocalDate.now(), "texto", 90);
    }
}
