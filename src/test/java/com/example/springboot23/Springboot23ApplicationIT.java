package com.example.springboot23;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"/schema.sql", "/data.sql"})
@Testcontainers
class Springboot23ApplicationIT {

    @LocalServerPort
    private int port;

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.2.0");

//    @BeforeAll
//    static void beforeAll() {
//        mysql.start();
//    }
//
//    @AfterAll
//    static void afterAll() {
//        mysql.stop();
//    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    void shouldGetAllCustomers() {
//        List<Customer> customers = List.of(
//                new Customer(null, "John", "john@mail.com"),
//                new Customer(null, "Dennis", "dennis@mail.com")
//        );
//        customerRepository.saveAll(customers);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/cities")
                .then()
                .statusCode(200)
                .body(".", hasSize(6));
    }
}
