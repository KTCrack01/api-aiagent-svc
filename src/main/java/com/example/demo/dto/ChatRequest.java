package com.example.demo.dto;

import lombok.Data;

@Data
public class ChatRequest {
    private String prompt;
    private String userId;
    private String model = "gpt-4o-mini"; // 기본값 설정
    private Integer maxTokens = 1000;
    private Double temperature = 0.7;
}
