package com.springAi.Google.Gemini20.Flash.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Request_Body {
    private String model;
    private List<Line_items> messages;
}
