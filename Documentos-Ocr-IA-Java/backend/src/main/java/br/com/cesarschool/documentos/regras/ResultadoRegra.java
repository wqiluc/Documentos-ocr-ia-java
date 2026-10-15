package br.com.cesarschool.documentos.regras;

import br.com.cesarschool.documentos.dominio.StatusValidacao;

/**
 * Resultado de uma regra. {@code statusSeFalhar} diz o que acontece com o cadastro quando a regra falha
 * (REPROVADO ou ANALISE_MANUAL). Uma regra "ignorada" não conta (ex.: o campo já faltou na R02).
 */
public record ResultadoRegra
(
        String codigo,
        String descricao,
        Situacao situacao,
        String motivo,
        StatusValidacao statusSeFalhar
) 
{

    public enum Situacao { ATENDIDA, FALHOU, IGNORADA }

    public static ResultadoRegra atendida(String codigo, String descricao) 
    {
        return new ResultadoRegra(codigo, descricao, Situacao.ATENDIDA, null, null);
    }

    public static ResultadoRegra falhou(String codigo, String descricao, String motivo) 
    {
        return new ResultadoRegra(codigo, descricao, Situacao.FALHOU, motivo, StatusValidacao.REPROVADO);
    }

    public static ResultadoRegra analiseManual(String codigo, String descricao, String motivo) 
    {
        return new ResultadoRegra(codigo, descricao, Situacao.FALHOU, motivo, StatusValidacao.ANALISE_MANUAL);
    }

    public static ResultadoRegra ignorada(String codigo, String descricao) 
    {
        return new ResultadoRegra(codigo, descricao, Situacao.IGNORADA, null, null);
    }

    public boolean isAtendida() 
    {
        return situacao == Situacao.ATENDIDA;
    }

    public boolean isFalhou() 
    {
        return situacao == Situacao.FALHOU;
    }
}