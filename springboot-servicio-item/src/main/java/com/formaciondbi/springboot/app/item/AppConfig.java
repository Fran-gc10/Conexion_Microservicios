package com.formaciondbi.springboot.app.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean("clienteRest")           //Con esta configuracion añadimos un RestTemplate con el que comunicarnos con recursos de otros Microservicios(ApiRest)
    public RestTemplate resgistrarRestTemplate(){
        return new RestTemplate();
    }
}
