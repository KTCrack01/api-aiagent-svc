package com.example.demo.dto;

import lombok.Data;

@Data
public class ChatResponse {
    private String prompt;
    private String response;
    private boolean success;
    private String error;
}
