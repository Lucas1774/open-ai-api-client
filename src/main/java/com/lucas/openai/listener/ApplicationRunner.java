package com.lucas.openai.listener;

import com.lucas.openai.configuration.ControllerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class ApplicationRunner implements CommandLineRunner {

    public ApplicationRunner(@Value("${server.port:8080}") String port, ControllerConfig config) {
        this.url = "http://localhost:" + port + config.getPath() + config.getFormPath();
    }

    private static final Map<String, Function<String, String[]>> OS_COMMANDS = new HashMap<>();
    private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ApplicationRunner.class);
    private final String url;

    static {
        OS_COMMANDS.put("win", urlInput -> new String[]{"cmd", "/c", "start", urlInput});
        OS_COMMANDS.put("mac", urlInput -> new String[]{"open", urlInput});
        OS_COMMANDS.put("nix", urlInput -> new String[]{"xdg-open", urlInput});
        OS_COMMANDS.put("nux", urlInput -> new String[]{"xdg-open", urlInput});
    }

    @Override
    public void run(String[] args) {
        openUrl(url);
    }

    private void openUrl(String url) {
        String os = System.getProperty("os.name").toLowerCase();

        OS_COMMANDS.entrySet().stream()
                .filter(entry -> os.contains(entry.getKey()))
                .findFirst()
                .map(Map.Entry::getValue)
                .map(func -> func.apply(url))
                .ifPresentOrElse(
                        this::startProcess,
                        () -> logger.warn("Unsupported OS: Open manually {}", url)
                );
    }

    private void startProcess(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder(args);
            pb.start();
        } catch (Exception e) {
            logger.error("Failed to open URL: {}", e.getMessage());
        }
    }
}
