package com.example.rest_api_assignment.config.api;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${swagger-info-title}")
    private String swaggerInfoTitle;

    @Value("${swagger-info-version}")
    private String swaggerInfoVersion;

    @Value("${swagger-info-description}")
    private String swaggerInfoDescription;

    @Value("${swagger-info-terms}")
    private String swaggerInfoTerms;

    @Bean
    public OpenAPI openAPI() {

        Server server = new Server();
        Info info = new Info()
                .title(swaggerInfoTitle)
                .version(swaggerInfoVersion)
                .description(swaggerInfoDescription)
                .termsOfService(swaggerInfoTerms);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}