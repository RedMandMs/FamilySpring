package ru.etu.mdp.family.presentation.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = { "ru.etu.mdp.family.servises",
    "ru.etu.mdp.family.controllers" })
public class WebAppConfig {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebAppConfig.class, args);
    }

}
