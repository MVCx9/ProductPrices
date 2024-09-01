package com.bcnc.productprices.infrastructure.rest.exception;

public class PriceNotFoundException extends ApiException {
    public PriceNotFoundException(String message) {
        super(message);
    }
}
