package com.acme;

import com.acme.dto.RentalDto;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RentalControllerTest {

    @Test
    public void testCreateRental_Test1() {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setToolCode("JAKR");
        rentalDto.setRentalDays(5);
        rentalDto.setDiscountPercent(101);
        rentalDto.setCheckoutDate("9/3/15");
        given()
                .contentType("application/json")
                .body(rentalDto)
                .when()
                .post("/rentals")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateRental_Test2() {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setToolCode("LADW");
        rentalDto.setRentalDays(3);
        rentalDto.setDiscountPercent(10);
        rentalDto.setCheckoutDate("7/2/20");
        given()
                .contentType("application/json")
                .body(rentalDto)
                .when()
                .post("/rentals")
                .then()
                .statusCode(201);
    }

    @Test
    public void testCreateRental_Test3() {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setToolCode("CHNS");
        rentalDto.setRentalDays(5);
        rentalDto.setDiscountPercent(25);
        rentalDto.setCheckoutDate("7/2/15");
        given()
                .contentType("application/json")
                .body(rentalDto)
                .when()
                .post("/rentals")
                .then()
                .statusCode(201);
    }

    @Test
    public void testCreateRental_Test4() {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setToolCode("JAKD");
        rentalDto.setRentalDays(6);
        rentalDto.setDiscountPercent(0);
        rentalDto.setCheckoutDate("9/3/15");
        given()
                .contentType("application/json")
                .body(rentalDto)
                .when()
                .post("/rentals")
                .then()
                .statusCode(201);
    }

    @Test
    public void testCreateRental_Test5() {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setToolCode("JAKR");
        rentalDto.setRentalDays(9);
        rentalDto.setDiscountPercent(0);
        rentalDto.setCheckoutDate("7/2/15");
        given()
                .contentType("application/json")
                .body(rentalDto)
                .when()
                .post("/rentals")
                .then()
                .statusCode(201);
    }

    @Test
    public void testCreateRental_Test6() {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setToolCode("JAKR");
        rentalDto.setRentalDays(4);
        rentalDto.setDiscountPercent(50);
        rentalDto.setCheckoutDate("7/2/20");
        given()
                .contentType("application/json")
                .body(rentalDto)
                .when()
                .post("/rentals")
                .then()
                .statusCode(201);
    }
}
