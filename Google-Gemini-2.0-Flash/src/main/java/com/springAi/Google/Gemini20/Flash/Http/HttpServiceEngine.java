package com.springAi.Google.Gemini20.Flash.Http;

import com.springAi.Google.Gemini20.Flash.ErrorHandling.ErrorCode;
import com.springAi.Google.Gemini20.Flash.ErrorHandling.Error_Response;
import com.springAi.Google.Gemini20.Flash.pojo.Request_Body;
import com.springAi.Google.Gemini20.Flash.pojo.Line_items;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Component
public class HttpServiceEngine {

    @Value("${spring.ai.openai.api-key}")
    private String Key;

    private final RestClient restClient;
    public HttpServiceEngine(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }


    public ResponseEntity<String> PreparingHttpRequest(String Prompt){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(Key);

            List<Line_items> messagesList = List.of(new Line_items(Prompt));
            Request_Body requestBody = new Request_Body();
            requestBody.setModel("gemini-2.0-flash");
            requestBody.setMessages(messagesList);


            ResponseEntity<String> response = restClient.method(HttpMethod.POST)
                    .uri("https://generativelanguage.googleapis.com/v1beta/openai/chat/completions")
                    .headers(httpHeaders -> httpHeaders.addAll(headers))
                    .body(requestBody)  // 👈 passing object, auto-converted to JSON
                    .retrieve()
                    .toEntity(String.class);
            log.info("Response from AI Service: {}", response);
            return response;
        }catch (HttpClientErrorException E) {
            log.error("Client error response from external service: {}", E.getResponseBodyAsString());
            throw new Error_Response(ErrorCode.CLINT_ERROR.getErrorCode(), ErrorCode.CLINT_ERROR.getErrorMessage(), HttpStatus.CONFLICT);
        }catch (HttpServerErrorException S){
            log.error("Server error response from external service: {}", S.getResponseBodyAsString());
            throw new Error_Response(ErrorCode.SERVER_ERROR.getErrorCode(), ErrorCode.SERVER_ERROR.getErrorMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            log.error("Generic error: {}", e.getMessage());
            throw new Error_Response(ErrorCode.GENERIC_ERROR.getErrorCode(), ErrorCode.GENERIC_ERROR.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
