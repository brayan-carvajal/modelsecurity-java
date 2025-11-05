package com.modelsecurity.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI modelSecurityOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ModelSecurity API")
                        .description("API REST para el sistema de seguridad con arquitectura en capas.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo ModelSecurity")
                                .email("soporte@modelsecurity.com")
                                .url("https://modelsecurity.com"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }
}
