package com.zara.prices;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.zara.prices.infrastructure.rest.util.Constants.PRICES_PATH;
import static com.zara.prices.infrastructure.rest.util.Constants.ZARA_PRICES_PATH;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceChallengeTest {

    @LocalServerPort
    private Integer port;

    private static String LOCALHOST = "http://localhost:";

    /**
     * Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void getPriceTest1() {
        RestAssured
                .when()
                .get(LOCALHOST + port + ZARA_PRICES_PATH + PRICES_PATH + "?date=2020-06-14T10:00:00&productId=35455&brandId=1")
                .then().log().body()
                .contentType("application/json")
                .statusCode(200).and()
                .body("productId", Matchers.equalTo(35455))
                .body("brandId", Matchers.equalTo(1))
                .body("rate", Matchers.equalTo(1.15F))
                .body("startDate", Matchers.equalTo("2020-06-14T00:00:00"))
                .body("endDate", Matchers.equalTo("2020-12-31T23:59:59"))
                .body("price", Matchers.is(40.82F))
                .extract();
    }

    /**
     * Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void getPriceTest2() {
        RestAssured
                .when()
                .get(LOCALHOST + port + ZARA_PRICES_PATH + PRICES_PATH + "?date=2020-06-14T16:00:00&productId=35455&brandId=1")
                .then().log().body()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.OK.value()).and()
                .body("productId", Matchers.equalTo(35455))
                .body("brandId", Matchers.equalTo(1))
                .body("rate", Matchers.equalTo(1.15F))
                .body("startDate", Matchers.equalTo("2020-06-14T00:00:00"))
                .body("endDate", Matchers.equalTo("2020-12-31T23:59:59"))
                .body("price", Matchers.is(40.82F))
                .extract();
    }

    /**
     * Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void getPriceTest3() {
        RestAssured
                .when()
                .get(LOCALHOST + port + ZARA_PRICES_PATH + PRICES_PATH + "?date=2020-06-14T21:00:00&productId=35455&brandId=1")
                .then().log().body()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.OK.value()).and()
                .body("productId", Matchers.equalTo(35455))
                .body("brandId", Matchers.equalTo(1))
                .body("rate", Matchers.equalTo(1.15F))
                .body("startDate", Matchers.equalTo("2020-06-14T00:00:00"))
                .body("endDate", Matchers.equalTo("2020-12-31T23:59:59"))
                .body("price", Matchers.is(40.82F))
                .extract();
    }

    /**
     * Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void getPriceTest4() {
        RestAssured
                .when()
                .get(LOCALHOST + port + ZARA_PRICES_PATH + PRICES_PATH + "?date=2020-06-15T10:00:00&productId=35455&brandId=1")
                .then().log().body()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.OK.value()).and()
                .body("productId", Matchers.equalTo(35455))
                .body("brandId", Matchers.equalTo(1))
                .body("rate", Matchers.equalTo(1.15F))
                .body("startDate", Matchers.equalTo("2020-06-14T00:00:00"))
                .body("endDate", Matchers.equalTo("2020-12-31T23:59:59"))
                .body("price", Matchers.is(40.82F))
                .extract();
    }

    /**
     * Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    void getPriceTest5() {
        RestAssured
                .when()
                .get(LOCALHOST + port + ZARA_PRICES_PATH + PRICES_PATH + "?date=2020-06-16T21:00:00&productId=35455&brandId=1")
                .then().log().body()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .statusCode(HttpStatus.OK.value()).and()
                .body("productId", Matchers.equalTo(35455))
                .body("brandId", Matchers.equalTo(1))
                .body("rate", Matchers.equalTo(1.15F))
                .body("startDate", Matchers.equalTo("2020-06-14T00:00:00"))
                .body("endDate", Matchers.equalTo("2020-12-31T23:59:59"))
                .body("price", Matchers.is(40.82F))
                .extract();
    }
}
