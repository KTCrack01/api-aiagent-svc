package com.example.demo.util;

import java.util.Arrays;
import java.util.List;

public class GPTModels {
    
    // GPT-5 Models
    public static final String GPT_5 = "gpt-5";
    public static final String GPT_5_MINI = "gpt-5-mini";
    public static final String GPT_5_NANO = "gpt-5-nano";
    public static final String GPT_5_CHAT_LATEST = "gpt-5-chat-latest";
    
    // GPT-4.1 Models
    public static final String GPT_4_1 = "gpt-4.1";
    public static final String GPT_4_1_MINI = "gpt-4.1-mini";
    public static final String GPT_4_1_NANO = "gpt-4.1-nano";
    
    // GPT-4o Models
    public static final String GPT_4O = "gpt-4o";
    public static final String GPT_4O_2024_05_13 = "gpt-4o-2024-05-13";
    public static final String GPT_4O_AUDIO_PREVIEW = "gpt-4o-audio-preview";
    public static final String GPT_4O_REALTIME_PREVIEW = "gpt-4o-realtime-preview";
    public static final String GPT_4O_MINI = "gpt-4o-mini";
    public static final String GPT_4O_MINI_AUDIO_PREVIEW = "gpt-4o-mini-audio-preview";
    public static final String GPT_4O_MINI_REALTIME_PREVIEW = "gpt-4o-mini-realtime-preview";
    public static final String GPT_4O_MINI_SEARCH_PREVIEW = "gpt-4o-mini-search-preview";
    public static final String GPT_4O_SEARCH_PREVIEW = "gpt-4o-search-preview";
    
    // O1 Models
    public static final String O1 = "o1";
    public static final String O1_PRO = "o1-pro";
    public static final String O1_MINI = "o1-mini";
    
    // O3 Models
    public static final String O3_PRO = "o3-pro";
    public static final String O3 = "o3";
    public static final String O3_DEEP_RESEARCH = "o3-deep-research";
    public static final String O3_MINI = "o3-mini";
    
    // O4 Models
    public static final String O4_MINI = "o4-mini";
    public static final String O4_MINI_DEEP_RESEARCH = "o4-mini-deep-research";
    
    // Codex Models
    public static final String CODEX_MINI_LATEST = "codex-mini-latest";
    
    // Computer Use Models
    public static final String COMPUTER_USE_PREVIEW = "computer-use-preview";
    
    // Image Models
    public static final String GPT_IMAGE_1 = "gpt-image-1";
    
    // Legacy Models (keeping for backward compatibility)
    public static final String GPT_4_TURBO = "gpt-4-turbo";
    public static final String GPT_3_5_TURBO = "gpt-3.5-turbo";
    
    public static final List<String> AVAILABLE_MODELS = Arrays.asList(
        // GPT-5 Models
        GPT_5,
        GPT_5_MINI,
        GPT_5_NANO,
        GPT_5_CHAT_LATEST,
        
        // GPT-4.1 Models
        GPT_4_1,
        GPT_4_1_MINI,
        GPT_4_1_NANO,
        
        // GPT-4o Models
        GPT_4O,
        GPT_4O_2024_05_13,
        GPT_4O_AUDIO_PREVIEW,
        GPT_4O_REALTIME_PREVIEW,
        GPT_4O_MINI,
        GPT_4O_MINI_AUDIO_PREVIEW,
        GPT_4O_MINI_REALTIME_PREVIEW,
        GPT_4O_MINI_SEARCH_PREVIEW,
        GPT_4O_SEARCH_PREVIEW,
        
        // O1 Models
        O1,
        O1_PRO,
        O1_MINI,
        
        // O3 Models
        O3_PRO,
        O3,
        O3_DEEP_RESEARCH,
        O3_MINI,
        
        // O4 Models
        O4_MINI,
        O4_MINI_DEEP_RESEARCH,
        
        // Codex Models
        CODEX_MINI_LATEST,
        
        // Computer Use Models
        COMPUTER_USE_PREVIEW,
        
        // Image Models
        GPT_IMAGE_1,
        
        // Legacy Models
        GPT_4_TURBO,
        GPT_3_5_TURBO
    );
    
    public static boolean isValidModel(String model) {
        return AVAILABLE_MODELS.contains(model);
    }
}