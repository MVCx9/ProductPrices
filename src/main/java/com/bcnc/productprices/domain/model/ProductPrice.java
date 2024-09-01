package com.bcnc.productprices.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRICES")
public class ProductPrice {

    @EmbeddedId
    private ProductPriceId id;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

}

