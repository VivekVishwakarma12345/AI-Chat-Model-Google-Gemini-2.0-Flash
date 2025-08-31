package com.springAi.Google.Gemini20.Flash.ErrorHandling;

import lombok.Getter;

@Getter
public enum ErrorCode {
    CLINT_ERROR("40001", "Client side error"),
    SERVER_ERROR("40002", "Server side error"),
    GENERIC_ERROR("50001", "Unable to process the request at this moment try again later"),
    UNABLE_TO_CONNECT("40003", "Unable to connect to stripe service");

    private final String errorCode;
    private final String errorMessage;

    ErrorCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
