package com.bcnc.productprices.infrastructure.rest.dto;

import com.bcnc.productprices.domain.entity.ProductPrice;
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

    public ProductPriceDto(ProductPrice pp) {
        this.productId = pp.getProductId().getId();
        this.brandId = pp.getBrandId().getId();
        this.priceList = pp.getPriceList();
        this.priority = pp.getPriority();
        this.startDate = pp.getStartDate();
        this.endDate = pp.getEndDate();
        this.price = pp.getPrice();
        this.currency = pp.getCurrency();
    }

}
