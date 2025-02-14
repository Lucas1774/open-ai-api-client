package com.lucas.openai.service;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.ChatCompletions;
import com.azure.ai.inference.models.ChatCompletionsOptions;
import com.azure.ai.inference.models.ChatRequestUserMessage;
import com.azure.core.credential.AzureKeyCredential;
import com.lucas.openai.configuration.OpenAIConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatCompletionService {
    private final OpenAIConfig config;

    public ChatCompletionService(OpenAIConfig config) {
        this.config = config;
    }

    public String getGeneratedMessage(String message) {
        ChatCompletionsClient client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(config.getKey()))
                .endpoint(config.getEndpoint())
                .buildClient();
        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(
                List.of(new ChatRequestUserMessage(message))
        );
        chatCompletionsOptions.setModel(config.getModel());
        ChatCompletions completions = client.complete(chatCompletionsOptions);
        return completions.getChoice().getMessage().getContent();
    }
}
