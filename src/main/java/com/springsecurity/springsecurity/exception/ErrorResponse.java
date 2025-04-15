package com.springsecurity.springsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private String requestURI;

    // Constructor to initialize fields
    public ErrorResponse(String message, HttpStatus httpStatus, String requestURI) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.requestURI = requestURI;
    }

    // Getters for the fields
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status, String path) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("status", status.value());
        errorDetails.put("error", status.getReasonPhrase());
        errorDetails.put("message", message);
        errorDetails.put("path", path);
        return new ResponseEntity<>(errorDetails, status);
    }
}
