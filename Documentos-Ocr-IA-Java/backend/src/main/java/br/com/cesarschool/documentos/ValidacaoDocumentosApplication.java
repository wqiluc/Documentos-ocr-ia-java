package br.com.cesarschool.documentos;

import java.time.Clock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ValidacaoDocumentosApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ValidacaoDocumentosApplication.class, args);
    }

    /** Relógio injetável: nos testes, um Clock fixo controla "hoje" na regra de 90 dias. */
    @Bean
    Clock clock()
    {
        return Clock.systemDefaultZone();
    }
}
