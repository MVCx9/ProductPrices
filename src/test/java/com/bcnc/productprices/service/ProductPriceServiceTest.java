package com.bcnc.productprices.service;

import com.bcnc.productprices.domain.model.ProductPrice;
import com.bcnc.productprices.domain.model.ProductPriceId;
import com.bcnc.productprices.domain.repository.ProductPriceRepository;
import com.bcnc.productprices.domain.service.ProductPriceService;
import com.bcnc.productprices.infrastructure.rest.exception.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductPriceServiceTest {

    @MockBean
    private ProductPriceRepository productPriceRepository;

    @InjectMocks
    private ProductPriceService productPriceService;

    @BeforeEach
    void setUp() {
        productPriceService = new ProductPriceService(productPriceRepository);
    }

    @Test
    void getAllPricesReturnsPrices() {
        List<ProductPrice> mockPrices = List.of(getDataExample(1L, 1L, LocalDateTime.now()));
        when(productPriceRepository.findAllPrices()).thenReturn(mockPrices);

        List<ProductPrice> result = productPriceService.getAllPrices();

        assertEquals(mockPrices, result);
    }

    @Test
    void getAllPricesThrowsPriceNotFoundExceptionWhenNoPrices() {
        when(productPriceRepository.findAllPrices()).thenReturn(Collections.emptyList());

        assertThrows(PriceNotFoundException.class, () -> productPriceService.getAllPrices());
    }

    @Test
    void getPriceByBrandAndProductReturnsPrices() {
        Long brandId = 1L;
        Long productId = 1L;

        List<ProductPrice> mockPrices = List.of(getDataExample(brandId, productId, LocalDateTime.now()));
        when(productPriceRepository.findByBrandAndProduct(brandId, productId)).thenReturn(mockPrices);

        List<ProductPrice> result = productPriceService.getPriceByBrandAndProduct(brandId, productId);

        assertEquals(mockPrices, result);
    }

    @Test
    void getPriceByBrandAndProductThrowsPriceNotFoundExceptionWhenNoPrices() {
        when(productPriceRepository.findByBrandAndProduct(any(), any())).thenReturn(Collections.emptyList());

        assertThrows(PriceNotFoundException.class, () -> productPriceService.getPriceByBrandAndProduct(1L, 1L));
    }

    @Test
    void getPriceByDateReturnsPrices() {
        Long brandId = 1L;
        Long productId = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();

        List<ProductPrice> mockPrices = List.of(getDataExample(brandId, productId, applicationDate));
        when(productPriceRepository.findPriceByDate(brandId, productId, applicationDate)).thenReturn(mockPrices);

        List<ProductPrice> result = productPriceService.getPriceByDate(brandId, productId, applicationDate);

        assertEquals(mockPrices, result);
    }

    @Test
    void getPriceByDateThrowsPriceNotFoundExceptionWhenNoPrices() {
        when(productPriceRepository.findPriceByDate(any(), any(), any())).thenReturn(Collections.emptyList());

        LocalDateTime localDate = LocalDateTime.now(); //Sonar issue
        assertThrows(PriceNotFoundException.class, () -> productPriceService.getPriceByDate(1L, 1L, localDate));
    }

    private static ProductPrice getDataExample(Long brandId, Long productId, LocalDateTime applicationDate) {
        ProductPriceId ppId = new ProductPriceId();
        ppId.setBrandId(brandId);
        ppId.setProductId(productId);
        ppId.setPriceList(1);
        ppId.setPriority(1);
        ProductPrice pp = new ProductPrice();
        pp.setId(ppId);
        pp.setPrice(BigDecimal.valueOf(55.0));
        pp.setStartDate(applicationDate.minusDays(1));
        pp.setEndDate(applicationDate.plusDays(1));
        pp.setCurrency("EUR");
        return pp;
    }
}