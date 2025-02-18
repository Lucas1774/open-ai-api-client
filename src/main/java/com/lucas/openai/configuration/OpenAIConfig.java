package com.lucas.openai.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@Getter
@ConfigurationProperties(prefix = "openai.api")
public class OpenAIConfig {
    private final String key;
    private final String endpoint;
    private final String model;
}
