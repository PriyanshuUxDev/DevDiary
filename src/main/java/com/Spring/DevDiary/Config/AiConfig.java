package com.Spring.DevDiary.Config;

import com.google.genai.Client;
import com.google.genai.types.HttpOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Bean
    public Client geminiClient() {
        HttpOptions httpOptions = HttpOptions.builder()
                .timeout(10000) // 10 seconds max wait for a response, in milliseconds
                .build();
        return Client.builder()
                .apiKey(apiKey)
                .httpOptions(httpOptions)
                .build();
    }
}