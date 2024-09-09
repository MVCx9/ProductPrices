package com.bcnc.productprices.domain.exception;

public class PriceNotFoundException extends ApiException {
    public PriceNotFoundException(String message) {
        super(message);
    }
}
