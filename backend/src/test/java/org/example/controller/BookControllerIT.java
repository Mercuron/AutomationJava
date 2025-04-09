package org.example.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIT {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        RestAssured.port=port;
    }

    @Test
    void whenGetBooks_thenStatus200(){
        given()
                .when()
                    .get("/api/books")
                .then()
                    .statusCode(200)
                    .body("size()",greaterThan(0));
    }
}
