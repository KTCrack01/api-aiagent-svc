package com.example.demo.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuator.health.Health;
import org.springframework.boot.actuator.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Component
public class OpenAIHealthIndicator implements HealthIndicator {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final WebClient webClient;

    public OpenAIHealthIndicator() {
        this.webClient = WebClient.builder().build();
    }

    @Override
    public Health health() {
        try {
            // OpenAI API 키 존재 확인
            if (apiKey == null || apiKey.trim().isEmpty() || apiKey.equals("${OPENAI_API_KEY}")) {
                return Health.down()
                    .withDetail("openai", "API key not configured")
                    .withDetail("status", "Configuration Error")
                    .build();
            }

            // OpenAI API URL 확인
            if (apiUrl == null || apiUrl.trim().isEmpty()) {
                return Health.down()
                    .withDetail("openai", "API URL not configured")
                    .withDetail("status", "Configuration Error")
                    .build();
            }

            // 간단한 모델 목록 요청으로 API 연결 테스트
            String response = webClient.get()
                    .uri(apiUrl + "/models")
                    .header("Authorization", "Bearer " + apiKey)
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(5))
                    .block();

            if (response != null && response.contains("data")) {
                return Health.up()
                    .withDetail("openai", "Connected")
                    .withDetail("api_url", apiUrl)
                    .withDetail("status", "API Available")
                    .build();
            } else {
                return Health.down()
                    .withDetail("openai", "Invalid response")
                    .withDetail("status", "API Error")
                    .build();
            }

        } catch (Exception ex) {
            return Health.down()
                .withDetail("openai", "Connection failed")
                .withDetail("error", ex.getMessage())
                .withDetail("status", "Connection Error")
                .build();
        }
    }
}
