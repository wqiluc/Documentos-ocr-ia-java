package br.com.cesarschool.documentos.regras;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

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

    @Test
    void nomeDiferenteVaiParaAnaliseManual() 
    {
        ResultadoRegra regra = this.regra.avaliar(comprovante("Maria da Silva"), cliente);
        assertThat(regra.isFalhou()).isTrue();
        assertThat(regra.statusSeFalhar()).isEqualTo(StatusValidacao.ANALISE_MANUAL);
    }

    private static ComprovanteResidencia comprovante(String titular) 
    {
        return new ComprovanteResidencia(titular, "Rua A, 1", "Centro", "Recife - PE", "50050-000",
                "Neoenergia", LocalDate.now(), "texto", 90);
    }
}
