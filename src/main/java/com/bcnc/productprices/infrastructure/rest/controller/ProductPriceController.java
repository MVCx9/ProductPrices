package com.bcnc.productprices.infrastructure.rest.controller;

import com.bcnc.productprices.api.ProductPriceApi;
import com.bcnc.productprices.domain.entity.ProductPrice;
import com.bcnc.productprices.domain.service.ProductPriceService;
import com.bcnc.productprices.infrastructure.rest.dto.ProductPriceDto;
import com.bcnc.productprices.utils.ParametersValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductPriceController implements ProductPriceApi {

    private final ProductPriceService productPriceService;

    @Autowired
    public ProductPriceController(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    @Override
    public ResponseEntity<List<ProductPriceDto>> getAllPrices() {
        List<ProductPrice> productPrice = productPriceService.getAllPrices();
        List<ProductPriceDto> productPriceDtos =
                productPrice.stream()
                            .map(ProductPriceDto::new)
                            .toList();

        return ResponseEntity.ok(productPriceDtos);
    }

    @Override
    public ResponseEntity<List<ProductPriceDto>> getPriceByBrandAndProduct(Long brandId, Long productId) {
        ParametersValidator.validateParameters(brandId, productId);
        List<ProductPrice> productPrice = productPriceService.getPriceByBrandAndProduct(brandId, productId);
        List<ProductPriceDto> productPriceDtos =
                productPrice.stream()
                        .map(ProductPriceDto::new)
                        .toList();

        return ResponseEntity.ok(productPriceDtos);
    }

    @Override
    public ResponseEntity<List<ProductPriceDto>> getPriceByDate(
        @RequestParam Long brandId,
        @RequestParam Long productId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate
    ) {
        ParametersValidator.validateParameters(brandId, productId, applicationDate);
        List<ProductPrice> productPrice = productPriceService.getPriceByDate(brandId, productId, applicationDate);
        List<ProductPriceDto> productPriceDtos =
                productPrice.stream()
                        .map(ProductPriceDto::new)
                        .toList();

        return ResponseEntity.ok(productPriceDtos);
    }


}