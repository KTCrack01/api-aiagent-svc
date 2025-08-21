package com.example.demo.controller;

import com.example.demo.dto.ChatRequest;
import com.example.demo.dto.ChatResponse;
import com.example.demo.dto.MessageRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.service.OpenAIService;
import com.example.demo.util.GPTModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/messages/send")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest request) {
        MessageResponse response = new MessageResponse();
        
        try {
            // 간단한 메시지 처리 (실제로는 더 복잡한 로직이 들어갈 수 있음)
            String processedMessage = "메시지가 성공적으로 처리되었습니다: " + request.getMessage();
            
            response.setMessage(request.getMessage());
            response.setResponse(processedMessage);
            response.setSuccess(true);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage(request.getMessage());
            response.setSuccess(false);
            response.setError(e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/chat")
    public Mono<ResponseEntity<ChatResponse>> sendChatPrompt(@RequestBody ChatRequest request) {
        return openAIService.generateResponse(request.getPrompt(), request.getModel())
                .map(gptResponse -> {
                    ChatResponse response = new ChatResponse();
                    response.setPrompt(request.getPrompt());
                    response.setResponse(gptResponse);
                    response.setSuccess(true);
                    return ResponseEntity.ok(response);
                })
                .onErrorResume(error -> {
                    ChatResponse response = new ChatResponse();
                    response.setPrompt(request.getPrompt());
                    response.setSuccess(false);
                    response.setError(error.getMessage());
                    return Mono.just(ResponseEntity.badRequest().body(response));
                });
    }

    @GetMapping("/models")
    public ResponseEntity<Map<String, Object>> getAvailableModels() {
        Map<String, Object> response = new HashMap<>();
        response.put("models", GPTModels.AVAILABLE_MODELS);
        return ResponseEntity.ok(response);
    }
}
