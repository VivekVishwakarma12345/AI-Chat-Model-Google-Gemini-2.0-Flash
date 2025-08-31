package com.springAi.Google.Gemini20.Flash.pojo;

import lombok.Data;
@Data
public class Line_items {
    private String role = "user";
    private String content;

    public Line_items(String prompt) {
        this.content = prompt;
    }
}
