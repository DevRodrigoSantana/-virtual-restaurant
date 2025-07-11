package com.restaurantevirtual.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("REST API VIRTUAL RESTAURANTE")
                                .description("API para gerenciamento de um restaurante virtual, incluindo pedidos, cardápio e controle de usuários.")
                                .version("V1")
                                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                                .contact(new Contact().name("Rodrigo Santana").email("rssantos.dev@gmail.com")));
    }
}
