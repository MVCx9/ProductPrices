package com.bcnc.productprices.infrastructure.input.api.controller;

import com.bcnc.productprices.application.port.input.GetProductPrice;
import com.bcnc.productprices.domain.model.ProductPrice;
import com.bcnc.productprices.application.service.GetProductPriceService;
import com.bcnc.productprices.infrastructure.input.api.dto.ProductPriceDto;
import com.bcnc.productprices.domain.utils.ParametersValidator;
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
public class GetProductPriceController implements GetProductPrice {

    private final GetProductPriceService getProductPriceService;

    @Autowired
    public GetProductPriceController(GetProductPriceService getProductPriceService) {
        this.getProductPriceService = getProductPriceService;
    }

    @Override
    public ResponseEntity<List<ProductPriceDto>> getAllPrices() {
        List<ProductPrice> productPrice = getProductPriceService.getAllPrices();
        List<ProductPriceDto> productPriceDtos =
                productPrice.stream()
                            .map(ProductPriceDto::new)
                            .toList();

        return ResponseEntity.ok(productPriceDtos);
    }

    @Override
    public ResponseEntity<List<ProductPriceDto>> getPriceByBrandAndProduct(Long brandId, Long productId) {
        ParametersValidator.validateParameters(brandId, productId);
        List<ProductPrice> productPrice = getProductPriceService.getPriceByBrandAndProduct(brandId, productId);
        List<ProductPriceDto> productPriceDtos =
                productPrice.stream()
                        .map(ProductPriceDto::new)
                        .toList();

        return ResponseEntity.ok(productPriceDtos);
    }

    @Override
    public ResponseEntity<ProductPriceDto> getPriceByDate(
        @RequestParam Long brandId,
        @RequestParam Long productId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate
    ) {
        ParametersValidator.validateParameters(brandId, productId, applicationDate);
        ProductPriceDto productPriceDto = new ProductPriceDto(getProductPriceService.getPriceByDate(brandId, productId, applicationDate));

        return ResponseEntity.ok(productPriceDto);
    }


}