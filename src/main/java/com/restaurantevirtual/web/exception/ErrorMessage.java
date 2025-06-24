package com.restaurantevirtual.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    private LocalDateTime timestamp;
    private String path;
    private String method;
    private int status;
    private String statusText;
    private String message;
    private Map<String,String> errors;

    //erro comum
    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message ) {
        this.timestamp = LocalDateTime.now();
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
    }

    //erro para validações
    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result) {
        this.timestamp = LocalDateTime.now();
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
        addErrors(result);
    }

    private void addErrors(BindingResult result) {

        this.errors = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()){
            this.errors.put(fieldError.getField(),fieldError.getDefaultMessage());

        }
    }
}
