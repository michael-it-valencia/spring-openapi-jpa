package com.michael.valencia.prototypes.api.template.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.michael.valencia.prototypes.api.template")
public class WebConfig {
    
}