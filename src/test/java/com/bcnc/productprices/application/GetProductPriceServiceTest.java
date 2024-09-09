package com.bcnc.productprices.application;

import com.bcnc.productprices.application.service.GetProductPriceService;
import com.bcnc.productprices.domain.model.Brands;
import com.bcnc.productprices.domain.model.ProductPrice;
import com.bcnc.productprices.domain.model.Products;
import com.bcnc.productprices.application.port.output.ProductPriceRepository;
import com.bcnc.productprices.infrastructure.exception.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GetProductPriceServiceTest {

    @MockBean
    private ProductPriceRepository productPriceRepository;

    @InjectMocks
    private GetProductPriceService getProductPriceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getProductPriceService = new GetProductPriceService(productPriceRepository);
    }

    @Test
    void getAllPricesReturnsPrices() {
        List<ProductPrice> mockPrices = List.of(getDataExample(1L, 1L, LocalDateTime.now()));
        when(productPriceRepository.findAllPrices()).thenReturn(mockPrices);

        List<ProductPrice> result = getProductPriceService.getAllPrices();

        assertAll(() -> {
            assertNotNull(result);
            assertFalse(result.isEmpty());
            result.forEach(productPrice -> assertNotNull(productPrice.getPrice()));
            assertEquals(mockPrices, result);
        });
    }

    @Test
    void getAllPricesThrowsPriceNotFoundExceptionWhenNoPrices() {
        when(productPriceRepository.findAllPrices()).thenReturn(Collections.emptyList());

        assertThrows(PriceNotFoundException.class, () -> getProductPriceService.getAllPrices());
    }

    @Test
    void getPriceByBrandAndProductReturnsPrices() {
        Long brandId = 1L;
        Long productId = 1L;

        List<ProductPrice> mockPrices = List.of(getDataExample(brandId, productId, LocalDateTime.now()));
        when(productPriceRepository.findByBrandAndProduct(brandId, productId)).thenReturn(mockPrices);

        List<ProductPrice> result = getProductPriceService.getPriceByBrandAndProduct(brandId, productId);

        assertAll(() -> {
            assertNotNull(result);
            assertFalse(result.isEmpty());
            result.forEach(productPrice -> assertNotNull(productPrice.getPrice()));
            assertEquals(mockPrices, result);
        });
    }

    @Test
    void getPriceByBrandAndProductThrowsPriceNotFoundExceptionWhenNoPrices() {
        when(productPriceRepository.findByBrandAndProduct(any(), any())).thenReturn(Collections.emptyList());

        assertThrows(PriceNotFoundException.class, () -> getProductPriceService.getPriceByBrandAndProduct(1L, 1L));
    }

    @Test
    void getPriceByDateReturnsPrices() {
        Long brandId = 1L;
        Long productId = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();

        List<ProductPrice> mockPrices = List.of(getDataExample(brandId, productId, applicationDate));
        when(productPriceRepository.findPriceByDate(brandId, productId, applicationDate)).thenReturn(mockPrices);

        List<ProductPrice> result = getProductPriceService.getPriceByDate(brandId, productId, applicationDate);

        assertAll(() -> {
            assertNotNull(result);
            assertFalse(result.isEmpty());
            result.forEach(productPrice -> assertNotNull(productPrice.getPrice()));
            assertEquals(mockPrices, result);
        });
    }

    @Test
    void getPriceByDateThrowsPriceNotFoundExceptionWhenNoPrices() {
        when(productPriceRepository.findPriceByDate(any(), any(), any())).thenReturn(Collections.emptyList());

        LocalDateTime localDate = LocalDateTime.now();
        assertThrows(PriceNotFoundException.class, () -> getProductPriceService.getPriceByDate(1L, 1L, localDate));
    }

    private static ProductPrice getDataExample(Long brandId, Long productId, LocalDateTime applicationDate) {
        Products p = new Products();
        p.setId(productId);
        p.setName("Product 1");
        p.setDescription("Description product 1");

        Brands b = new Brands();
        b.setId(brandId);
        b.setName("Brand 1");

        ProductPrice pp = new ProductPrice();
        pp.setProductId(p);
        pp.setBrandId(b);
        pp.setPriceList(1);
        pp.setPriority(1);
        pp.setPrice(BigDecimal.valueOf(55.0));
        pp.setStartDate(applicationDate.minusDays(1));
        pp.setEndDate(applicationDate.plusDays(1));
        pp.setCurrency("EUR");
        return pp;
    }
}