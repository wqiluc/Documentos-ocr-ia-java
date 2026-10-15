package br.com.cesarschool.documentos.dto;

/**
 * Corpo do POST de comprovante. Envie {@code conteudoDocumento} (texto já extraído),
 * {@code imagemBase64} (imagem/PDF em base64) ou ambos.
 */
public record ValidacaoRequest
(
        String nomeArquivo,
        String mimeType,
        String conteudoDocumento,
        String imagemBase64
)
{}