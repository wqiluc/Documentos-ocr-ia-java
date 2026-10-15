package br.com.cesarschool.documentos.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record AppProperties(Ai ai, Ocr ocr, OpenCv opencv, Regras regras, Cors cors) 
{

    public record Ai(OpenAi openai)
    {}

    public record OpenAi(String apiKey, String model, String url) 
    {
        public boolean configurada() 
        {
            return apiKey != null && !apiKey.isBlank();
        }
    }

    public record Ocr(String command, String tessdataPath, String language)
    {}

    public record OpenCv(String command)
    {}

    public record Regras(double confiancaMinima, int caracteresMinimos, int diasMaximosEmissao)
    {}

    public record Cors(List<String> origens)
    {}
}