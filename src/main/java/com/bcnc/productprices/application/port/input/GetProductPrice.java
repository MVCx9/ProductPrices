package com.bcnc.productprices.application.port.input;

import com.bcnc.productprices.infrastructure.api.dto.ProductPriceDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

@Validated
public interface GetProductPrice {

    /**
     * Retrieves all prices.
     *
     * @return ResponseEntity containing a ProductPriceDto with all prices.
     */
    @GetMapping("/prices")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<List<ProductPriceDto>> getAllPrices();

    /**
     * Retrieves the price by brand and product.
     *
     * @param brandId the ID of the brand.
     * @param productId the ID of the product.
     * @return ResponseEntity containing a ProductPriceDto with the price information.
     */
    @GetMapping("/prices_brand-product")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<List<ProductPriceDto>> getPriceByBrandAndProduct(
            @RequestParam Long brandId,
            @RequestParam Long productId
    );

    /**
     * Retrieves the price by date.
     *
     * @param brandId the ID of the brand.
     * @param productId the ID of the product.
     * @param applicationDate the date and time of application.
     * @return ResponseEntity containing a ProductPriceDto with the price information.
     */
    @GetMapping("/prices_date")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<List<ProductPriceDto>> getPriceByDate(
            @RequestParam Long brandId,
            @RequestParam Long productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate
    );

}
