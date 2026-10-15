package br.com.cesarschool.documentos.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.cesarschool.documentos.dominio.StatusValidacao;
import br.com.cesarschool.documentos.dto.ValidacaoRequest;
import br.com.cesarschool.documentos.dto.ValidacaoResponse;
import br.com.cesarschool.documentos.exception.ClienteNaoEncontradoException;

@SpringBootTest(properties = "app.ai.openai.api-key=")
class ValidacaoServiceTest 
{

    @Autowired
    private ValidacaoService service;

    @Test
    void aprovaComprovanteRecenteNoNomeDoCliente() 
    {
        ValidacaoResponse r = service.validarComprovante(1L, request("Joao da Silva", "50050-000", 10));

        assertThat(r.resultado().status()).isEqualTo(StatusValidacao.APROVADO);
        assertThat(r.resultado().motivos()).isEmpty();
        assertThat(r.resultado().regrasAtendidas()).hasSize(5);
        assertThat(r.dadosExtraidos().cep()).isEqualTo("50050-000");
        assertThat(r.etapas()).hasSize(5);

        ValidacaoResponse consultado = service.buscar(1L, r.validacaoId());
        assertThat(consultado.resultado().status()).isEqualTo(StatusValidacao.APROVADO);
    }

    @Test
    void reprovaComprovanteAntigo() 
    {
        ValidacaoResponse r = service.validarComprovante(1L, request("Joao da Silva", "50050-000", 120));

        assertThat(r.resultado().status()).isEqualTo(StatusValidacao.REPROVADO);
        assertThat(r.resultado().motivos()).anyMatch(m -> m.contains("90 dias"));
    }

    @Test
    void titularDiferenteVaiParaAnaliseManual() 
    {
        ValidacaoResponse r = service.validarComprovante(1L, request("Carlos da Silva", "50050-000", 10));

        assertThat(r.resultado().status()).isEqualTo(StatusValidacao.ANALISE_MANUAL);
    }

    @Test
    void reprovacaoTemPrioridadeSobreAnaliseManual() 
    {
        ValidacaoResponse r = service.validarComprovante(1L, request("Carlos da Silva", "123", 10));

        assertThat(r.resultado().status()).isEqualTo(StatusValidacao.REPROVADO);
    }

    @Test
    void clienteInexistente() 
    {
        assertThatThrownBy(() -> service.validarComprovante(999L, request("Joao da Silva", "50050-000", 10)))
                .isInstanceOf(ClienteNaoEncontradoException.class);
    }

    private static ValidacaoRequest request(String titular, String cep, int diasAtras) {
        String emissao = LocalDate.now().minusDays(diasAtras).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String texto = """
                CONTA DE ENERGIA
                Titular: %s
                Endereco: Rua das Flores, 150
                Bairro: Boa Vista
                Cidade: Recife - PE
                CEP: %s
                Data de emissão: %s""".formatted(titular, cep, emissao);
        return new ValidacaoRequest("comprovante.pdf", "application/pdf", texto, null);
    }
}