package br.com.cesarschool.documentos.regras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.cesarschool.documentos.config.AppProperties;
import br.com.cesarschool.documentos.dominio.Cliente;
import br.com.cesarschool.documentos.dominio.ComprovanteResidencia;

/** R01 — o OCR retornou texto suficiente e com boa confiança. */
@Component
@Order(1)
public class RegraLegibilidade implements Regra 
{

    static final String CODIGO = "R01";
    static final String DESCRICAO = "Documento legível";

    private final double confiancaMinima;
    private final int caracteresMinimos;

    @Autowired
    public RegraLegibilidade(AppProperties props) 
    {
        this(props.regras().confiancaMinima(), props.regras().caracteresMinimos());
    }

    RegraLegibilidade(double confiancaMinima, int caracteresMinimos) 
    {
        this.confiancaMinima = confiancaMinima;
        this.caracteresMinimos = caracteresMinimos;
    }

    @Override
    public ResultadoRegra avaliar(ComprovanteResidencia comprovante, Cliente cliente) 
    {
        String texto = comprovante.textoOcr() == null ? "" : comprovante.textoOcr().strip();

        if (texto.length() < caracteresMinimos) 
        {
            return ResultadoRegra.falhou(CODIGO, DESCRICAO,
                    "Documento ilegível: pouco texto foi reconhecido. Envie uma foto nítida, bem iluminada e sem cortes.");
        }

        if (comprovante.confiancaOcr() < confiancaMinima) 
        {
            return ResultadoRegra.falhou(CODIGO, DESCRICAO,
            "Documento ilegível: confiança do OCR de %.0f%% (mínimo %.0f%%). Envie uma imagem mais nítida."
            .formatted(comprovante.confiancaOcr(), confiancaMinima));
        }
        return ResultadoRegra.atendida(CODIGO, DESCRICAO);
    }
}