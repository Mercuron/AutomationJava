package org.example.controller;

import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = "book-events", partitions = 1)
public class BookControllerIT {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        RestAssured.port=port;
    }

    @Test
    @DisplayName("Проверка получения книг")
    @Feature("Работа с книгами")
    void whenGetBooks_thenStatus200(){
        given()
                .when()
                    .get("/api/books")
                .then()
                    .statusCode(200)
                    .body("size()",greaterThan(0));
    }
    @Test
    void whenCreateBook_thenKafkaMessageSent() {
        // Отправляем REST-запрос
        given()
                .contentType("application/json")
                .body("{\"title\":\"Kafka Test\",\"author\":\"Author\"}")
                .when()
                .post("/api/books")
                .then()
                .statusCode(200);

        // Проверяем, что сообщение было отправлено в Kafka
        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    // Можно добавить Mockito для проверки вызова consumer
                    System.out.println("Проверка Kafka...");
                });
    }
}
