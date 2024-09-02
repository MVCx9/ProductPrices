package com.bcnc.productprices.domain.repository;

import com.bcnc.productprices.domain.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

    @Query("SELECT p FROM ProductPrice p ORDER BY p.id.brandId, p.id.productId, p.id.priceList, p.id.priority DESC")
    List<ProductPrice> findAllPrices();

    @Query("SELECT p FROM ProductPrice p WHERE p.id.brandId = :brandId AND p.id.productId = :productId AND :applicationDate BETWEEN p.startDate AND p.endDate ORDER BY p.id.priority DESC")
    List<ProductPrice> findPriceByDate(@Param("brandId") Long brandId, @Param("productId") Long productId, @Param("applicationDate") LocalDateTime applicationDate);

    @Query("SELECT p FROM ProductPrice p WHERE p.id.brandId = :brandId AND p.id.productId = :productId ORDER BY p.id.priority DESC")
    List<ProductPrice> findByBrandAndProduct(@Param("brandId") Long brandId, @Param("productId") Long productId);

}