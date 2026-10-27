package br.com.cesarschool.documentos.regras;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import br.com.cesarschool.documentos.dominio.Cliente;
import br.com.cesarschool.documentos.dominio.ComprovanteResidencia;
import br.com.cesarschool.documentos.dominio.StatusValidacao;

class RegraEmissao90DiasTest
{

    private static final LocalDate HOJE = LocalDate.of(2026, 10, 8);
    private final Clock clock = Clock.fixed(HOJE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    private final RegraEmissao90Dias regra = new RegraEmissao90Dias(clock, 90);
    private final Cliente cliente = new Cliente(1L, "Joao da Silva", "52998224725");

    @Test
    void aprovaComprovanteRecente()
    {
        assertThat(regra.avaliar(comprovante(HOJE.minusDays(10)), cliente).isAtendida()).isTrue();
    }

    @Test
    void aprovaComprovanteEmitidoHoje()
    {
        assertThat(regra.avaliar(comprovante(HOJE), cliente).isAtendida()).isTrue();
    }

    @Test
    void aprovaNoLimiteDe90Dias()
    {
        assertThat(regra.avaliar(comprovante(HOJE.minusDays(90)), cliente).isAtendida()).isTrue();
    }

    @ParameterizedTest

    @ValueSource(ints = {1, 30, 45, 89})
    void aprovaDentroDoPrazo(int diasAtras)
    {
        assertThat(regra.avaliar(comprovante(HOJE.minusDays(diasAtras)), cliente).isAtendida()).isTrue();
    }

    @Test
    void reprovaComMaisDe90Dias()
    {
        ResultadoRegra regra = this.regra.avaliar(comprovante(HOJE.minusDays(91)), cliente);
        assertThat(regra.isFalhou()).isTrue();
        assertThat(regra.motivo()).contains("excedeu o prazo de 90 dias❌");
    }

    @ParameterizedTest

    @ValueSource(ints = {92, 120, 365, 1000})
    void reprovaForaDoPrazo(int diasAtras)
    {
        ResultadoRegra resultado = regra.avaliar(comprovante(HOJE.minusDays(diasAtras)), cliente);
        assertThat(resultado.isFalhou()).isTrue();
        assertThat(resultado.motivo()).contains("(%d dias)".formatted(diasAtras));
    }

    @Test
    void reprovacaoPorPrazoReprovaOCadastro()
    {
        ResultadoRegra resultado = regra.avaliar(comprovante(HOJE.minusDays(91)), cliente);
        assertThat(resultado.codigo()).isEqualTo("R06");
        assertThat(resultado.statusSeFalhar()).isEqualTo(StatusValidacao.REPROVADO);
        assertThat(resultado.motivo()).contains("(91 dias)").contains("mais recente");
    }

    @Test
    void reprovaDataNoFuturo()
    {
        assertThat(regra.avaliar(comprovante(HOJE.plusDays(1)), cliente).isFalhou()).isTrue();
    }

    @Test
    void dataNoFuturoInformaDataNoMotivo()
    {
        LocalDate amanha = HOJE.plusDays(1);
        ResultadoRegra resultado = regra.avaliar(comprovante(amanha), cliente);
        assertThat(resultado.statusSeFalhar()).isEqualTo(StatusValidacao.REPROVADO);
        assertThat(resultado.motivo()).contains("futuro").contains(amanha.toString());
    }

    @Test
    void reprovaDataMuitoNoFuturo()
    {
        assertThat(regra.avaliar(comprovante(HOJE.plusYears(1)), cliente).isFalhou()).isTrue();
    }

    @Test
    void ignoraQuandoNaoHaData()
    {
        assertThat(regra.avaliar(comprovante(null), cliente).situacao()).isEqualTo(ResultadoRegra.Situacao.IGNORADA);
    }

    @Test
    void respeitaPrazoConfigurado()
    {
        RegraEmissao90Dias regra30 = new RegraEmissao90Dias(clock, 30);

        assertThat(regra30.avaliar(comprovante(HOJE.minusDays(30)), cliente).isAtendida()).isTrue();

        ResultadoRegra resultado = regra30.avaliar(comprovante(HOJE.minusDays(31)), cliente);
        assertThat(resultado.isFalhou()).isTrue();
        assertThat(resultado.motivo()).contains("mais de 30 dias").contains("(31 dias)");
    }

    @Test
    void prazoZeroSoAceitaEmissaoDeHoje()
    {
        RegraEmissao90Dias regraZero = new RegraEmissao90Dias(clock, 0);

        assertThat(regraZero.avaliar(comprovante(HOJE), cliente).isAtendida()).isTrue();
        assertThat(regraZero.avaliar(comprovante(HOJE.minusDays(1)), cliente).isFalhou()).isTrue();
    }

    @Test
    void contaDiasCorretamenteNaViradaDoAno()
    {
        LocalDate inicioDoAno = LocalDate.of(2027, 1, 15);
        Clock clockJaneiro = Clock.fixed(inicioDoAno.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        RegraEmissao90Dias regraJaneiro = new RegraEmissao90Dias(clockJaneiro, 90);

        assertThat(regraJaneiro.avaliar(comprovante(LocalDate.of(2026, 10, 17)), cliente).isAtendida()).isTrue();
        assertThat(regraJaneiro.avaliar(comprovante(LocalDate.of(2026, 10, 16)), cliente).isFalhou()).isTrue();
    }

    private static ComprovanteResidencia comprovante(LocalDate emissao)
    {
        return new ComprovanteResidencia("Joao da Silva", "Rua A, 1", "Centro", "Recife - PE", "50050-000",
                "Neoenergia", emissao, "texto", 90);
    }
}