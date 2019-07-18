package com.sundar.microservices.customer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean(name = "rest-template")
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }
}
