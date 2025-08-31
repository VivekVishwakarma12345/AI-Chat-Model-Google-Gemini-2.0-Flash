package com.springAi.Google.Gemini20.Flash.ErrorHandling;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Error_Response extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    public Error_Response(String errorCode, String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}