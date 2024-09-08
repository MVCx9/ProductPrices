package com.bcnc.productprices.infrastructure.exception;

// General API Exception
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
