package com.bcnc.productprices.domain.exception;

// General API Exception
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
