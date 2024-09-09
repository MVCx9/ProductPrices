package com.bcnc.productprices.web;

import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductPriceComponentTest {

    @Autowired
    private WebApplicationContext context;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        MockMvc mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void getAllPricesReturnsPrices() {

        RestAssured.given()
        .when()
            .get("/api/prices")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("size()", equalTo(10));
    }

    @Test
    void getPriceByBrandAndProductReturnsPrices() {

        RestAssured.given()
            .param("brandId", 1)
            .param("productId", 35455)
        .when()
            .get("/api/prices_brand-product")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("size()", equalTo(4))
            .body("[0].price", equalTo(25.45f))
            .body("[1].price", equalTo(30.5f))
            .body("[2].price", equalTo(38.95f))
            .body("[3].price", equalTo(35.5f))
            .body("[0].brandId", equalTo(1))
            .body("[0].productId", equalTo(35455));
    }

    @Test
    void getPriceByBrandAndProductThrowsParameterError() {

        // No productId
        RestAssured.given()
            .param("brandId", 1)
        .when()
            .get("/api/prices_brand-product")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void getPriceByDateReturnsPrices_14_10() {

        //Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        RestAssured.given()
            .param("brandId", 1)
            .param("productId", 35455)
            .param("applicationDate", "2020-06-14T10:00:00")
        .when()
            .get("/api/prices_date")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("size()", equalTo(1))
            .body("[0].price", equalTo(35.50f))
            .body("[0].priceList", equalTo(1))
            .body("[0].priority", equalTo(0))
            .body("[0].brandId", equalTo(1))
            .body("[0].productId", equalTo(35455));
    }

    @Test
    void getPriceByDateReturnsPrices_14_16() {

        //Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        RestAssured.given()
            .param("brandId", 1)
            .param("productId", 35455)
            .param("applicationDate", "2020-06-14T16:00:00")
        .when()
            .get("/api/prices_date")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("size()", equalTo(1))
            .body("[0].price", equalTo(25.45f))
            .body("[0].priority", equalTo(1))
            .body("[0].priceList", equalTo(2))
            .body("[0].brandId", equalTo(1))
            .body("[0].productId", equalTo(35455));
    }

    @Test
    void getPriceByDateReturnsPrices_14_21() {

        //Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        RestAssured.given()
            .param("brandId", 1)
            .param("productId", 35455)
            .param("applicationDate", "2020-06-14T21:00:00")
        .when()
            .get("/api/prices_date")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("size()", equalTo(1))
            .body("[0].price", equalTo(35.50f))
            .body("[0].priceList", equalTo(1))
            .body("[0].brandId", equalTo(1))
            .body("[0].productId", equalTo(35455));
    }

    @Test
    void getPriceByDateReturnsPrices_15_10() {

        //Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
        RestAssured.given()
            .param("brandId", 1)
            .param("productId", 35455)
            .param("applicationDate", "2020-06-15T10:00:00")
        .when()
            .get("/api/prices_date")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("size()", equalTo(1))
            .body("[0].priceList", equalTo(3))
            .body("[0].priority", equalTo(1))
            .body("[0].price", equalTo(30.5f))
            .body("[0].brandId", equalTo(1))
            .body("[0].productId", equalTo(35455));
    }

    @Test
    void getPriceByDateReturnsPrices_16_21() {

        //Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
        RestAssured.given()
            .param("brandId", 1)
            .param("productId", 35455)
            .param("applicationDate", "2020-06-16T21:00:00")
        .when()
            .get("/api/prices_date")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("size()", equalTo(1))
            .body("[0].priceList", equalTo(4))
            .body("[0].priority", equalTo(1))
            .body("[0].price", equalTo(38.95f))
            .body("[0].brandId", equalTo(1))
            .body("[0].productId", equalTo(35455));
    }

    @Test
    void getPriceByDateThrowsParameterError() {

        // No applicationDate
        RestAssured.given()
            .param("brandId", 1)
            .param("productId", 35455)
        .when()
            .get("/api/prices_date")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
