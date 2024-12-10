package io.oz;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.lessThan;

public class CompletedTransactionsApiTest extends ApiTest {
    private static final String PATH = "/completedTransactions";

    /*
    Calling https://api.v1.dev.chargelab.io/core/v1/completedTransactions gives 403 on unauthorized requests
    Created test to confirm 403 status code
    */
    @Test
    public void testCompletedTransactionsApi_Returns_403() {
        RestAssured.given(SPECIFICATION)
                .get(PATH)
                .then()
                .statusCode(SC_FORBIDDEN)
                .time(lessThan(DEFAULT_REQUEST_TIMEOUT));
    }

    /*
    Since it's not possible to cover positive case of calling https://api.v1.dev.chargelab.io/core/v1/completedTransactions
    Testing how API reacts on invalid auth token
    */
    @Test
    public void testCompletedTransactionsApi_Returns_401() {
        RestAssured.given(SPECIFICATION)
                .header(new Header(AUTHORIZATION, "x-auth invalidToken"))
                .get(PATH)
                .then()
                .statusCode(SC_UNAUTHORIZED)
                .time(lessThan(DEFAULT_REQUEST_TIMEOUT));
    }
}
