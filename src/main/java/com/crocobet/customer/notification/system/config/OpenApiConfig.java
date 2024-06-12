package com.crocobet.customer.notification.system.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Service")
                        .description("This is the API service")
                        .version("v1"))
                .addSecurityItem(new SecurityRequirement().addList("BasicAuth"))
                .components(new Components()
                        .addSecuritySchemes("BasicAuth", new SecurityScheme()
                                .name("BasicAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")));


    }
}