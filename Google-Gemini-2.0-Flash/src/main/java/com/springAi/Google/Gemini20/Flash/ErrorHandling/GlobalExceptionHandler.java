package com.springAi.Google.Gemini20.Flash.ErrorHandling;


import com.springAi.Google.Gemini20.Flash.Constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Error_Response.class)
    public ResponseEntity<Map<String, String>> handleStripeProviderException(Error_Response ex) {
        log.error("Handling Error_Response: {}", ex.getErrorMessage());
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put(Constant.ERROR_CODE, ex.getErrorCode());
        errorBody.put(Constant.ERROR_MESSAGE, ex.getErrorMessage());
        log.error("Handled Error_Response: {}", errorBody);
        return new ResponseEntity<>(errorBody, ex.getHttpStatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllOtherExceptions(Exception ex) {
        log.error("Handling generic exception: {}", ex.getMessage());
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put(Constant.ERROR_CODE, ErrorCode.GENERIC_ERROR.getErrorCode());
        errorBody.put(Constant.ERROR_MESSAGE,ErrorCode.GENERIC_ERROR.getErrorMessage());
        log.error("Handled generic exception: {}", errorBody);
        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}