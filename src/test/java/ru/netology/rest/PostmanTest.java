package ru.netology.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import org.json.JSONObject;

class PostmanTest {

    @Test
    void shouldReturn201OnPost() {
        var key = "msg";
        var value = "Привет REST!";
        var requestBody = new JSONObject();

        requestBody.put(key, value);

        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json; charset=UTF-8")
                .accept(ContentType.JSON)
                .body(requestBody.toString())
        .when()
                .log()
                    .body()
                .post("post")
        .then()
                .log()
                    .ifValidationFails()
                .assertThat()
                    .statusCode(201)
                        .and()
                    .body("data." + key, is(equalTo(value)))
        ;
    }
}
