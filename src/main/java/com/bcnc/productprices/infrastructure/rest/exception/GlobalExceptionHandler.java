package com.bcnc.productprices.infrastructure.rest.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Data
    private static class SimpleErrorResponse {
        private String className;
        private int status;
        private String message;
    }

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<SimpleErrorResponse> handlePriceNotFoundException(PriceNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ParametersException.class)
    public ResponseEntity<SimpleErrorResponse> handleParametersException(ParametersException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    private ResponseEntity<SimpleErrorResponse> buildErrorResponse (HttpStatus status, String message) {
        SimpleErrorResponse errorResponse = new SimpleErrorResponse();
        errorResponse.setClassName(this.getClass().getSimpleName());
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(message);
        return ResponseEntity.status(status).body(errorResponse);
    }
}

