package com.bcnc.productprices.utils;


import com.bcnc.productprices.infrastructure.rest.exception.ParametersException;

import java.time.LocalDateTime;

public class ParametersValidator {

    private ParametersValidator() {}

    public static void validateParameters(Long brandId, Long productId) {
        if (brandId == null || brandId <= 0) {
            throw new ParametersException(Constants.BRAND_PARAMETER_NOT_VALID);
        }

        if (productId == null || productId <= 0) {
            throw new ParametersException(Constants.PRODUCT_PARAMETER_NOT_VALID);
        }
    }

    public static void validateParameters(Long brandId, Long productId, LocalDateTime applicationDate) {
        validateParameters(brandId, productId);

        if (applicationDate == null) {
            throw new ParametersException(Constants.DATE_PARAMETER_NOT_VALID);
        }

    }
}

