package br.com.cesarschool.documentos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer
{

    private final AppProperties propriedades;

    public CorsConfig(AppProperties propriedades)
    {
        this.propriedades = propriedades;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        registry.addMapping("/api/**")
                .allowedOrigins(propriedades.cors().origens().toArray(String[]::new))
                .allowedMethods("GET", "POST", "OPTIONS");
    }
}
