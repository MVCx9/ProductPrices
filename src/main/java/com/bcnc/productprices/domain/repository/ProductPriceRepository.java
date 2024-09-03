package com.bcnc.productprices.domain.repository;

import com.bcnc.productprices.domain.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

    @Query("SELECT p FROM ProductPrice p ORDER BY p.brandId.id, p.productId.id, p.priceList, p.priority DESC")
    List<ProductPrice> findAllPrices();

    @Query("SELECT p FROM ProductPrice p WHERE p.brandId.id = :brandId AND p.productId.id = :productId AND :applicationDate BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC")
    List<ProductPrice> findPriceByDate(@Param("brandId") Long brandId, @Param("productId") Long productId, @Param("applicationDate") LocalDateTime applicationDate);

    @Query("SELECT p FROM ProductPrice p WHERE p.brandId.id = :brandId AND p.productId.id = :productId ORDER BY p.priority DESC")
    List<ProductPrice> findByBrandAndProduct(@Param("brandId") Long brandId, @Param("productId") Long productId);

}