package com.bcnc.productprices.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "PRICES")
@IdClass(ProductPriceId.class)
public class ProductPrice {

    @Id
    @ManyToOne
    @JoinColumn(name = "BRAND_ID", nullable = false)
    private Brands brandId;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Products productId;

    @Id
    @Column(name = "PRICE_LIST", nullable = false)
    private Integer priceList;

    @Id
    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

}

