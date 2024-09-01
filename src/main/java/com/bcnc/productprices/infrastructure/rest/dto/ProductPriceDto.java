package com.bcnc.productprices.infrastructure.rest.dto;

import com.bcnc.productprices.domain.model.ProductPrice;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductPriceDto {

    private Long productId;
    private Long brandId;
    private Integer priceList;
    private Integer priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
    private String currency;

    public ProductPriceDto(ProductPrice price) {
        this.productId = price.getId().getProductId();
        this.brandId = price.getId().getBrandId();
        this.priceList = price.getId().getPriceList();
        this.priority = price.getId().getPriority();
        this.startDate = price.getStartDate();
        this.endDate = price.getEndDate();
        this.price = price.getPrice();
        this.currency = price.getCurrency();
    }

}
