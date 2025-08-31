package com.springAi.Google.Gemini20.Flash.Services;

import com.springAi.Google.Gemini20.Flash.Http.HttpServiceEngine;
import com.springAi.Google.Gemini20.Flash.Util.JSON_Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AI_Services {
    @Autowired
    private HttpServiceEngine httpServiceEngine;
    @Autowired
    private JSON_Util json_util;
    public String CreateAiResponse(String Prompt){
        ResponseEntity<String> response = httpServiceEngine.PreparingHttpRequest(Prompt);
        String responce =  json_util.ConvertJsonToJavaObject(response.getBody());
        log.info("AI Response: {}", responce);
        return responce;
    }
}
