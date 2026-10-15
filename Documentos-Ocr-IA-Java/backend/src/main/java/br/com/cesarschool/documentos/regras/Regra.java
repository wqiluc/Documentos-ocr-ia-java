package br.com.cesarschool.documentos.regras;

import br.com.cesarschool.documentos.dominio.Cliente;
import br.com.cesarschool.documentos.dominio.ComprovanteResidencia;

/** Uma regra de negócio aplicada ao comprovante de residência de um cliente. */
public interface Regra 
{

    ResultadoRegra avaliar(ComprovanteResidencia comprovante, Cliente cliente);
}