package com.wojnarowicz.sfg.restmvc.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        Contact contact = new Contact("Veeteq", "vvv@vvv.com", "vv@gmail.com");
        return new ApiInfo ("Spring Framework MVC", 
                "Spring Framework MVC course by John Thompson", 
                "1.0", 
                "termsOfServiceUrl", 
                contact, 
                "Apache License Version 2.0", 
                "http://www.apache.org/licenses/LICENSE-2.0.html", 
                new ArrayList<>());
    }
}
