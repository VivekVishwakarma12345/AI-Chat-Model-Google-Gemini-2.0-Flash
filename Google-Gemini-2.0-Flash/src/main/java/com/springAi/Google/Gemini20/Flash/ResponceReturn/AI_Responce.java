package com.springAi.Google.Gemini20.Flash.ResponceReturn;

import lombok.Data;
import java.util.List;

@Data
public class AI_Responce {
    private List<Choice> choices;
}