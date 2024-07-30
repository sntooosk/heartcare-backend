package com.etec.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Heartcare - backend", version = "1", description = "API desenvolvida para o backend do Heart Care"))
@EnableScheduling
public class HeartCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeartCareApplication.class, args);
    }

}
