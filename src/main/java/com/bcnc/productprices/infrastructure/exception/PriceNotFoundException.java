package com.bcnc.productprices.infrastructure.exception;

public class PriceNotFoundException extends ApiException {
    public PriceNotFoundException(String message) {
        super(message);
    }
}
