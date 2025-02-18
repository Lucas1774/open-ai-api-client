package com.lucas.openai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class OpenAIAPIClient {

    public static void main(String[] args) {
        SpringApplication.run(OpenAIAPIClient.class, args);
    }
}
