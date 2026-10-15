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

/** R06 — comprovante emitido há no máximo 90 dias. */
@Component
@Order(3)
public class RegraEmissao90Dias implements Regra 
{

    static final String CODIGO = "R06";
    static final String DESCRICAO = "Emissão dentro de 90 dias";

    private final Clock clock;
    private final int diasMaximos;

    @Autowired
    public RegraEmissao90Dias(Clock clock, AppProperties props) 
    {
        this(clock, props.regras().diasMaximosEmissao());
    }

    RegraEmissao90Dias(Clock clock, int diasMaximos) 
    {
        this.clock = clock;
        this.diasMaximos = diasMaximos;
    }

    @Override
    public ResultadoRegra avaliar(ComprovanteResidencia comprovante, Cliente cliente) 
    {
        LocalDate emissao = comprovante.dataEmissao();
        if (emissao == null) 
        {
            return ResultadoRegra.ignorada(CODIGO, DESCRICAO); 
        }

        LocalDate hoje = LocalDate.now(clock);
        if (emissao.isAfter(hoje)) 
        {
            return ResultadoRegra.falhou(CODIGO, DESCRICAO,
                    "Data de emissão do comprovante (%s) está no futuro. Verifique o documento.".formatted(emissao));
        }

        long dias = ChronoUnit.DAYS.between(emissao, hoje);
        
        if (dias > diasMaximos) 
        {
            return ResultadoRegra.falhou(CODIGO, DESCRICAO,
                    "Comprovante emitido há mais de %d dias (%d dias). Envie um comprovante mais recente."
                            .formatted(diasMaximos, dias));
        }

        return ResultadoRegra.atendida(CODIGO, DESCRICAO);
    }
}
