package com.example.springboot23;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class Springboot23Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot23Application.class, args);
    }

    @Bean
    public RestClient createClient() {
        return RestClient.builder()
                .baseUrl("https://localhost:8080/")
                .build();
    }
}
