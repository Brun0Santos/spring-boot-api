package com.bruno.api.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customerOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rest API Java and Spring")
                        .version("v1")
                        .description("Some description about your API")
                        .termsOfService("bruno@teste.com")
                        .license(new License().name("Apache 2.0").url("bruno@teste.com"))
                );
    }
}
