package com.sundar.microservices.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Authentication Service")
                .apiInfo(this.metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sundar.microservices.auth.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Authentication API",
                "Authentication API documentation",
                "1.0",
                "Terms of service", null, null, null);
        return apiInfo;
    }
}
