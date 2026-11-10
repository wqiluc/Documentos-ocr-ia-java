package br.com.cesarschool.documentos.dto;

/** Uma etapa do pipeline exibida na tela: número, nome e o que aconteceu nela. */
public record EtapaPipeline(int ordem, String nome, String resultado)
{}
