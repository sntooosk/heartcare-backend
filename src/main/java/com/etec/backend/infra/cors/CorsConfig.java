package com.etec.backend.infra.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://heartcare-web-app.vercel.app", "http://localhost:4200",
                        "http://localhost:8081" , "http://localhost:8080")
                .allowedMethods("GET", "POST", "DELETE", "PUT");
    }
}
