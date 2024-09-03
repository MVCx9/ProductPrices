package com.bcnc.productprices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.bcnc.productprices.domain.entity")
public class ProductPricesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductPricesApplication.class, args);
    }

}
