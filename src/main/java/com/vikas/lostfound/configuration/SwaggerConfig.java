package com.vikas.lostfound.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()

                .servers(List.of(
                        new Server()
                                .url("https://lostfound-tracker.up.railway.app")
                                .description("Railway Server")
                ))

                .info(new Info()
                        .title("Lost And Found API")
                        .version("1.0"))

                .schemaRequirement(
                        "basicAuth",

                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")
                );
    }
}