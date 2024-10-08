package com.bcnc.productprices.infrastructure.output.persistence.repository;

import com.bcnc.productprices.domain.repository.ProductPriceRepository;
import com.bcnc.productprices.domain.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaProductPriceRepository extends JpaRepository<ProductPrice, Long>, ProductPriceRepository {

    @Override
    @Query("SELECT p FROM ProductPrice p ORDER BY p.brandId.id, p.productId.id, p.priceList, p.priority DESC")
    List<ProductPrice> findAllPrices();

    @Override
    @Query("SELECT p FROM ProductPrice p WHERE p.brandId.id = :brandId AND p.productId.id = :productId AND :applicationDate BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC LIMIT 1")
    ProductPrice findPriceByDate(@Param("brandId") Long brandId, @Param("productId") Long productId, @Param("applicationDate") LocalDateTime applicationDate);

    @Override
    @Query("SELECT p FROM ProductPrice p WHERE p.brandId.id = :brandId AND p.productId.id = :productId ORDER BY p.priority DESC")
    List<ProductPrice> findByBrandAndProduct(@Param("brandId") Long brandId, @Param("productId") Long productId);
}
