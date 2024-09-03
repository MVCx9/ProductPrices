package com.bcnc.productprices.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductPriceId implements Serializable {

    private Long brandId;

    private Long productId;

    private Integer priceList;

    private Integer priority;
}