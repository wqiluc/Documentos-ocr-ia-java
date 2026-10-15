package br.com.cesarschool.documentos.regras;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import br.com.cesarschool.documentos.dominio.Cliente;
import br.com.cesarschool.documentos.dominio.ComprovanteResidencia;

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
    void aprovaNoLimiteDe90Dias() 
    {
        assertThat(regra.avaliar(comprovante(HOJE.minusDays(90)), cliente).isAtendida()).isTrue();
    }

    @Test
    void reprovaComMaisDe90Dias() 
    {
        ResultadoRegra regra = this.regra.avaliar(comprovante(HOJE.minusDays(91)), cliente);
        assertThat(regra.isFalhou()).isTrue();
        assertThat(regra.motivo()).contains("mais de 90 dias");
    }

    @Test
    void reprovaDataNoFuturo() 
    {
        assertThat(regra.avaliar(comprovante(HOJE.plusDays(1)), cliente).isFalhou()).isTrue();
    }

    @Test
    void ignoraQuandoNaoHaData() 
    {
        assertThat(regra.avaliar(comprovante(null), cliente).situacao()).isEqualTo(ResultadoRegra.Situacao.IGNORADA);
    }

    private static ComprovanteResidencia comprovante(LocalDate emissao) 
    {
        return new ComprovanteResidencia("Joao da Silva", "Rua A, 1", "Centro", "Recife - PE", "50050-000",
                "Neoenergia", emissao, "texto", 90);
    }
}
