package com.bcnc.productprices.web;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductPriceComponentTest {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:8080/api";
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPricesReturnsPrices() {

        RestAssured.given()
        .when()
            .get("/prices")
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
            .get("/prices_brand-product")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("size()", equalTo(4));
    }

    @Test
    void getPriceByBrandAndProductThrowsParameterError() {

        // No productId
        RestAssured.given()
            .param("brandId", 1)
        .when()
            .get("/prices_brand-product")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void getPriceByDateReturnsPrices_14_10() {
        //Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        RestAssured.given()
            .param("brandId", 1)
            .param("productId", 35455)
            .param("applicationDate", "2020-06-14T10:00:00")
        .when()
            .get("/prices_date")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void getPriceByDateReturnsPrices_14_16() {
        //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        RestAssured.given()
            .param("brandId", 1)
            .param("productId", 35455)
            .param("applicationDate", "2020-06-14T16:00:00")
        .when()
            .get("/prices_date")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void getPriceByDateReturnsPrices_14_21() {

        //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        RestAssured.given()
            .param("brandId", 1)
            .param("productId", 35455)
            .param("applicationDate", "2020-06-14T21:00:00")
        .when()
            .get("/prices_date")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void getPriceByDateReturnsPrices_15_10() {

        //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
        RestAssured.given()
            .param("brandId", 1)
            .param("productId", 35455)
            .param("applicationDate", "2020-06-15T10:00:00")
        .when()
            .get("/prices_date")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void getPriceByDateReturnsPrices_16_21() {

        //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
        RestAssured.given()
            .param("brandId", 1)
            .param("productId", 35455)
            .param("applicationDate", "2020-06-16T21:00:00")
        .when()
            .get("/prices_date")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void getPriceByDateThrowsParameterError() {

        // No applicationDate
        RestAssured.given()
            .param("brandId", 1)
            .param("productId", 35455)
        .when()
            .get("/prices_date")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
