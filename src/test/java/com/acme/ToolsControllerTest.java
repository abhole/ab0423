package com.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
public class ToolsControllerTest  { //extends BaseControllerTest {

    @Test
    public void testGetToolsEndpoint() {
        given()
          .when().get("/tools")
                .then()
                .statusCode(200);
    }

}