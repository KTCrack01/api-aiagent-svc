package com.example.demo.dto;

import lombok.Data;

@Data
public class MessageResponse {
    private String message;
    private String response;
    private boolean success;
    private String error;
}
