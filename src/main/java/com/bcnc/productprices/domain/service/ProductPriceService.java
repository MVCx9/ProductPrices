package com.bcnc.productprices.domain.service;

import com.bcnc.productprices.contants.Constants;
import com.bcnc.productprices.domain.model.ProductPrice;
import com.bcnc.productprices.domain.repository.ProductPriceRepository;
import com.bcnc.productprices.infrastructure.rest.exception.PriceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ProductPriceService {

    private final ProductPriceRepository productPriceRepository;

    public ProductPriceService(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    public List<ProductPrice> getAllPrices() {
        List<ProductPrice> prices = productPriceRepository.findAllPrices();

        if (prices.isEmpty()) {
            throw new PriceNotFoundException(Constants.NOT_FOUND_MESSAGE);
        }

        return prices;
    }

    public List<ProductPrice> getPriceByBrandAndProduct(Long brandId, Long productId) {

        List<ProductPrice> prices = productPriceRepository.findByBrandAndProduct(brandId, productId);

        if (prices.isEmpty()) {
            throw new PriceNotFoundException(Constants.NOT_FOUND_MESSAGE);
        }

        return prices;
    }

    public List<ProductPrice> getPriceByDate(Long brandId, Long productId, LocalDateTime applicationDate) {

        List<ProductPrice> prices = productPriceRepository.findPriceByDate(brandId, productId, applicationDate);

        if (prices.isEmpty()) {
            throw new PriceNotFoundException(Constants.NOT_FOUND_MESSAGE);
        }

        return prices;
    }
}