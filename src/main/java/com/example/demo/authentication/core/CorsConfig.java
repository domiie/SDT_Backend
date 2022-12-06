package com.example.demo.authentication.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer
{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // ak bude iny link pre registraciu, tak by ho malo stacit pridat do tohto addMapping:
        // .addMapping("/api/authentication", "/api/registration")

        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("Authorization")
                .exposedHeaders("Authorization");
    }
}