package com.bcnc.productprices.domain.repository;

import com.bcnc.productprices.domain.model.ProductPrice;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductPriceRepository {

    List<ProductPrice> findAllPrices();
    List<ProductPrice> findByBrandAndProduct(Long brandId, Long productId);
    List<ProductPrice> findPriceByDate(Long brandId, Long productId, LocalDateTime applicationDate);
}
