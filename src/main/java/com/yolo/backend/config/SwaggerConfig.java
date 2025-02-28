package com.yolo.backend.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Yolo API challenge documentation")
                        .version("1.0")
                        .description("Yolo fullstack junior software engineer challenge.")
                        .contact(new Contact()
                                .name("Support")
                                .email("support@example.com")));
    }
}
