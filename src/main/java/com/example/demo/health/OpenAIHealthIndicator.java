package com.example.demo.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuator.health.Health;
import org.springframework.boot.actuator.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class OpenAIHealthIndicator implements HealthIndicator {

    @Value("${openai.api.key:}")
    private String apiKey;

    @Value("${openai.api.url:}")
    private String apiUrl;

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
            if (apiUrl == null || apiUrl.trim().isEmpty() || apiUrl.equals("${OPENAI_API_URL}")) {
                return Health.down()
                    .withDetail("openai", "API URL not configured")
                    .withDetail("status", "Configuration Error")
                    .build();
            }

            // 기본적인 설정 확인만 수행 (실제 API 호출 없이)
            return Health.up()
                .withDetail("openai", "Configured")
                .withDetail("api_url", apiUrl)
                .withDetail("api_key_length", apiKey.length())
                .withDetail("status", "Configuration Valid")
                .build();

        } catch (Exception ex) {
            return Health.down()
                .withDetail("openai", "Configuration check failed")
                .withDetail("error", ex.getMessage())
                .withDetail("status", "Error")
                .build();
        }
    }
}
