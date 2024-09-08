package com.bcnc.productprices.application.port.output;

import com.bcnc.productprices.domain.model.ProductPrice;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductPriceRepository {

    List<ProductPrice> findAllPrices();
    List<ProductPrice> findByBrandAndProduct(Long brandId, Long productId);
    List<ProductPrice> findPriceByDate(Long brandId, Long productId, LocalDateTime applicationDate);
}
