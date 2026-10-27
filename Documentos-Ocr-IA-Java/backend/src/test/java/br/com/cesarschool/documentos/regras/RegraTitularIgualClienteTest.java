package br.com.cesarschool.documentos.regras;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import br.com.cesarschool.documentos.dominio.Cliente;
import br.com.cesarschool.documentos.dominio.ComprovanteResidencia;
import br.com.cesarschool.documentos.dominio.StatusValidacao;

class RegraTitularIgualClienteTest
{

    private final RegraTitularIgualCliente regra = new RegraTitularIgualCliente();
    private final Cliente cliente = new Cliente(1L, "João da Silva", "52998224725");

    @Test
    void aceitaMesmoNomeIgnorandoAcentoCaixaEEspacos()
    {
        assertThat(regra.avaliar(comprovante("JOAO  DA SILVA"), cliente).isAtendida()).isTrue();
    }

    @ParameterizedTest

    @ValueSource(strings = {"João da Silva", "joão da silva", "  João da Silva  ", "JOÃO DA SILVA", "Joao\tda\nSilva", "João-da-Silva", "João da Silva."})
    void aceitaVariacoesDoMesmoNome(String titular)
    {
        assertThat(regra.avaliar(comprovante(titular), cliente).isAtendida()).isTrue();
    }

    @Test
    void aceitaNomeComCedilhaETilSemAcento()
    {
        Cliente conceicao = new Cliente(2L, "Maria da Conceição Araújo", "11144477735");
        assertThat(regra.avaliar(comprovante("MARIA DA CONCEICAO ARAUJO"), conceicao).isAtendida()).isTrue();
    }

    @Test
    void nomeDiferenteVaiParaAnaliseManual()
    {
        ResultadoRegra regra = this.regra.avaliar(comprovante("Maria da Silva"), cliente);
        assertThat(regra.isFalhou()).isTrue();
        assertThat(regra.statusSeFalhar()).isEqualTo(StatusValidacao.ANALISE_MANUAL);
    }

    @ParameterizedTest

    @ValueSource(strings = {"João Silva", "João Pedro da Silva", "João da Silva Filho", "Joana da Silva", "Silva da João"})
    void nomeParcialOuComPalavrasDiferentesVaiParaAnaliseManual(String titular)
    {
        ResultadoRegra resultado = regra.avaliar(comprovante(titular), cliente);
        assertThat(resultado.isFalhou()).isTrue();
        assertThat(resultado.statusSeFalhar()).isEqualTo(StatusValidacao.ANALISE_MANUAL);
    }

    @Test
    void motivoInformaTitularSemEspacosEClienteCadastrado()
    {
        ResultadoRegra resultado = regra.avaliar(comprovante("  Maria da Silva  "), cliente);
        assertThat(resultado.codigo()).isEqualTo("R08");
        assertThat(resultado.motivo())
                .contains("\"Maria da Silva\"")
                .contains("\"João da Silva\"")
                .contains("análise manual");
    }

    @ParameterizedTest

    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t\n"})
    void ignoraQuandoNaoHaTitular(String titular)
    {
        ResultadoRegra resultado = regra.avaliar(comprovante(titular), cliente);
        assertThat(resultado.situacao()).isEqualTo(ResultadoRegra.Situacao.IGNORADA);
        assertThat(resultado.statusSeFalhar()).isNull();
    }

    @Test
    void titularIgualNaoTemMotivo()
    {
        ResultadoRegra resultado = regra.avaliar(comprovante("João da Silva"), cliente);
        assertThat(resultado.codigo()).isEqualTo("R08");
        assertThat(resultado.motivo()).isNull();
        assertThat(resultado.statusSeFalhar()).isNull();
    }

    @ParameterizedTest

    @CsvSource(delimiter = '|', quoteCharacter = '"', value = 
    {
            "João da Silva|joao da silva",
            "  JOSÉ   DOS  SANTOS  |jose dos santos",
            "Ana-Lúcia O'Neil|ana lucia o neil",
            "Conceição Araújo|conceicao araujo",
            "Pedro 2º Souza|pedro souza"
    })
    void normalizaRemovendoAcentosPontuacaoEEspacosExtras(String nome, String esperado)
    {
        assertThat(RegraTitularIgualCliente.normalizar(nome)).isEqualTo(esperado);
    }

    private static ComprovanteResidencia comprovante(String titular)
    {
        return new ComprovanteResidencia(titular, "Rua A, 1", "Centro", "Recife - PE", "50050-000",
                "Neoenergia", LocalDate.now(), "texto", 90);
    }
}