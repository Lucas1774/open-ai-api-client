package com.lucas.openai.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@Getter
@ConfigurationProperties(prefix = "controller")
public class ControllerConfig {
    private final String path;
    private final String formPath;
}
