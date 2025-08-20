package com.example.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OpenAIService {

    @Value("${openai.api.key:}")
    private String apiKey;

    @Value("${openai.api.url:https://api.openai.com/v1/chat/completions}")
    private String apiUrl;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public OpenAIService() {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public Mono<String> generateResponse(String prompt) {
        return generateResponse(prompt, "gpt-4o-mini", 1000, 0.7);
    }

    public Mono<String> generateResponse(String prompt, String model, Integer maxTokens, Double temperature) {
        if (apiKey == null || apiKey.isEmpty()) {
            return Mono.error(new RuntimeException("OpenAI API key is not configured"));
        }

        ObjectNode requestBody = objectMapper.createObjectNode();
        requestBody.put("model", model);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", temperature);

        ArrayNode messages = objectMapper.createArrayNode();
        ObjectNode message = objectMapper.createObjectNode();
        message.put("role", "user");
        message.put("content", prompt);
        messages.add(message);
        requestBody.set("messages", messages);

        return webClient.post()
                .uri(apiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> {
                    try {
                        return response.get("choices").get(0).get("message").get("content").asText();
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to parse OpenAI response: " + e.getMessage());
                    }
                })
                .onErrorMap(error -> new RuntimeException("OpenAI API call failed: " + error.getMessage()));
    }
}
