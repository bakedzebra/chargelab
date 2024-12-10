package io.oz;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiTest {
    final static String BASE_URL = "https://api.v1.dev.chargelab.io/core/v1";

    final static Long DEFAULT_REQUEST_TIMEOUT = 2000L;

    final static RequestSpecification SPECIFICATION = RestAssured.given()
            .baseUri(BASE_URL)
            .accept(ContentType.JSON);
}
