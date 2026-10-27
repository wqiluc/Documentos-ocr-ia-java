package br.com.cesarschool.documentos.regras;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import br.com.cesarschool.documentos.dominio.Cliente;
import br.com.cesarschool.documentos.dominio.ComprovanteResidencia;
import br.com.cesarschool.documentos.dominio.StatusValidacao;

class RegraCepValidoTest
{

    private final RegraCepValido regra = new RegraCepValido();
    private final Cliente cliente = new Cliente(1L, "Joao da Silva", "52998224725");

    @ParameterizedTest

    @ValueSource(strings = {"50050-000", "50050000", " 01310-100 ", "\t12345-678\n", "01001000"})
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

    @ParameterizedTest

    @ValueSource(strings = {"500500-00", "50050--000", "50050 000", "-50050000", "50050000-", "5005O-000", "CEP 50050-000"})
    void rejeitaCepComHifenOuCaracteresForaDoLugar(String cep)
    {
        assertThat(regra.avaliar(comprovante(cep), cliente).isFalhou()).isTrue();
    }

    @ParameterizedTest

    @ValueSource(strings = {"11111111", "99999-999", "55555-555"})
    void rejeitaCepComTodosOsDigitosIguais(String cep)
    {
        assertThat(regra.avaliar(comprovante(cep), cliente).isFalhou()).isTrue();
    }

    @ParameterizedTest

    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t"})
    void ignoraQuandoNaoHaCep(String cep)
    {
        ResultadoRegra resultado = regra.avaliar(comprovante(cep), cliente);
        assertThat(resultado.situacao()).isEqualTo(ResultadoRegra.Situacao.IGNORADA);
        assertThat(resultado.motivo()).isNull();
    }

    @Test
    void falhaReprovaEInformaCepNoMotivo()
    {
        ResultadoRegra resultado = regra.avaliar(comprovante("5005-000"), cliente);
        assertThat(resultado.codigo()).isEqualTo("R07");
        assertThat(resultado.statusSeFalhar()).isEqualTo(StatusValidacao.REPROVADO);
        assertThat(resultado.motivo()).contains("\"5005-000\"").contains("8 dígitos");
    }

    @Test
    void cepValidoNaoTemMotivoNemStatusDeFalha()
    {
        ResultadoRegra resultado = regra.avaliar(comprovante("50050-000"), cliente);
        assertThat(resultado.codigo()).isEqualTo("R07");
        assertThat(resultado.descricao()).isEqualTo("CEP válido");
        assertThat(resultado.motivo()).isNull();
        assertThat(resultado.statusSeFalhar()).isNull();
    }

    private static ComprovanteResidencia comprovante(String cep)
    {
        return new ComprovanteResidencia("Joao da Silva", "Rua A, 1", "Centro", "Recife - PE", cep,
                "Neoenergia", LocalDate.now(), "texto", 90);
    }
}