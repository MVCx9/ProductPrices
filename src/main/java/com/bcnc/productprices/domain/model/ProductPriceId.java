package com.bcnc.productprices.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class ProductPriceId implements Serializable {

    @Column(name = "BRAND_ID", nullable = false)
    private Long brandId;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "PRICE_LIST", nullable = false)
    private Integer priceList;

    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;
}