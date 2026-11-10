package br.com.cesarschool.documentos.regras;

import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.cesarschool.documentos.config.AppProperties;
import br.com.cesarschool.documentos.dominio.Cliente;
import br.com.cesarschool.documentos.dominio.ComprovanteResidencia;

/** R06 — comprovante emitido há no máximo 90 dias (prazo configurável em app.regras.dias-maximos-emissao). */
@Component
@Order(3)
public class RegraEmissao90Dias implements Regra
{

    static final String CODIGO = "R06";
    static final String DESCRICAO = "Emissão dentro de 90 dias";

    private final Clock clock;
    private final int prazoMaximoEmDias;

    @Autowired
    public RegraEmissao90Dias(Clock clock, AppProperties propriedades)
    {
        this(clock, propriedades.regras().diasMaximosEmissao());
    }

    RegraEmissao90Dias(Clock clock, int prazoMaximoEmDias)
    {
        this.clock = clock;
        this.prazoMaximoEmDias = prazoMaximoEmDias;
    }

    @Override
    public ResultadoRegra avaliar(ComprovanteResidencia comprovante, Cliente cliente)
    {
        LocalDate dataEmissao = comprovante.dataEmissao();

        if (dataEmissao == null)
        {
            return ResultadoRegra.ignorada(CODIGO, DESCRICAO); // já reportado na R02
        }

        LocalDate hoje = LocalDate.now(clock);

        if (dataEmissao.isAfter(hoje))
        {
            return ResultadoRegra.falhou(CODIGO, DESCRICAO,
                    "Data de emissão do comprovante (%s) está no futuro. Verifique o documento.".formatted(dataEmissao));
        }

        long diasDesdeAEmissao = ChronoUnit.DAYS.between(dataEmissao, hoje);

        if (diasDesdeAEmissao > prazoMaximoEmDias)
        {
            return ResultadoRegra.falhou(CODIGO, DESCRICAO,
                    "Comprovante emitido há mais de %d dias (%d dias) e excedeu o prazo de %d dias❌. Envie um comprovante mais recente."
                            .formatted(prazoMaximoEmDias, diasDesdeAEmissao, prazoMaximoEmDias));
        }

        return ResultadoRegra.atendida(CODIGO, DESCRICAO);
    }
}
