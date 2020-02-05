package com.wojnarowicz.sfg.gw.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties
@PropertySource("classpath:auth.properties")
@Getter
@Setter
public class ExternalProperties {

    private String auth;
 
    private String authStr;
}
