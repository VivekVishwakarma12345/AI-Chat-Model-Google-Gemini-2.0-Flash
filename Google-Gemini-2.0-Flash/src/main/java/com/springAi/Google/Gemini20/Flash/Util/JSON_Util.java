package com.springAi.Google.Gemini20.Flash.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springAi.Google.Gemini20.Flash.ResponceReturn.AI_Responce;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class JSON_Util {
    private final ObjectMapper objectMapper;

    public <T> T ConvertJsonToJavaObject(String responseEntity) {
        try {
            AI_Responce response = objectMapper.readValue(responseEntity, AI_Responce.class);
            String content = response.getChoices().get(0).getMessage().getContent();
            return (T) content;
        } catch (Exception e) {
            log.error("Error parsing JSON response: {}", e.getMessage());
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }

}
