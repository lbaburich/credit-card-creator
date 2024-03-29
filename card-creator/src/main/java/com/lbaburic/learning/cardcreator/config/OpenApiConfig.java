package com.lbaburic.learning.cardcreator.config;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Credit Card Creator",
                description = "OpenAPI documentation for Credit Card Creator",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:9092"
                )
        }
)
public class OpenApiConfig {
}
