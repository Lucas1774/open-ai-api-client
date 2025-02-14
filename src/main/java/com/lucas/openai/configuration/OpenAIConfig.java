package com.lucas.openai.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "openai.api")
public class OpenAIConfig {
    private String key;
    private String endpoint;
    private String model;
}
