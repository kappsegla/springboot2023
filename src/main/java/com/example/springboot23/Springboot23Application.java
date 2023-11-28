package com.example.springboot23;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Springboot23Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot23Application.class, args);
    }


}
