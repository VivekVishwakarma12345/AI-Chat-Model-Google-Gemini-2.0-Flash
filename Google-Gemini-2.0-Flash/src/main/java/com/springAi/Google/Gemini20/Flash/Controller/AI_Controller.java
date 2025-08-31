package com.springAi.Google.Gemini20.Flash.Controller;

import com.springAi.Google.Gemini20.Flash.Constant.Constant;
import com.springAi.Google.Gemini20.Flash.Services.AI_Services;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(Constant.AI)
@RequiredArgsConstructor
public class AI_Controller {

    private final AI_Services ai_services;

    @PostMapping
    public String getAIResponse(@RequestBody String prompt) {
        return ai_services.CreateAiResponse(prompt);
    }
}
