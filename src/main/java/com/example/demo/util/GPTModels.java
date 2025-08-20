package com.example.demo.util;

import java.util.Arrays;
import java.util.List;

public class GPTModels {
    
    public static final String GPT_4O_MINI = "gpt-4o-mini";
    public static final String GPT_4O = "gpt-4o";
    public static final String GPT_4_TURBO = "gpt-4-turbo";
    public static final String GPT_3_5_TURBO = "gpt-3.5-turbo";
    
    public static final List<String> AVAILABLE_MODELS = Arrays.asList(
        GPT_4O_MINI,
        GPT_4O,
        GPT_4_TURBO,
        GPT_3_5_TURBO
    );
    
    public static boolean isValidModel(String model) {
        return AVAILABLE_MODELS.contains(model);
    }
    
    public static String getDefaultModel() {
        return GPT_4O_MINI;
    }
}
