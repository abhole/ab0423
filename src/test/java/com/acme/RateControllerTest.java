package com.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RateControllerTest {

    @Test
    public void testGetToolsEndpoint() {
        given()
            .when().get("/rates")
            .then()
            .statusCode(200);
    }
}
