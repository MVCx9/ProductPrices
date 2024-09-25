package com.bcnc.productprices.application.service;

import com.bcnc.productprices.domain.exception.PriceNotFoundException;
import com.bcnc.productprices.domain.model.ProductPrice;
import com.bcnc.productprices.domain.repository.ProductPriceRepository;
import com.bcnc.productprices.domain.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GetProductPriceService {

    private final ProductPriceRepository productPriceRepository;

    @Autowired
    public GetProductPriceService(ProductPriceRepository productPriceRepository) {
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

    public ProductPrice getPriceByDate(Long brandId, Long productId, LocalDateTime applicationDate) {

        ProductPrice prices = productPriceRepository.findPriceByDate(brandId, productId, applicationDate);

        if (prices == null) {
            throw new PriceNotFoundException(Constants.NOT_FOUND_MESSAGE);
        }

        return prices;
    }
}