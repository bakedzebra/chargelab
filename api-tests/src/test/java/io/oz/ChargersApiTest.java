package io.oz;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ChargersApiTest extends ApiTest {
    private static final String PATH = "/chargers";

    private static final String EXISTING_ID = "/018ab9c1-55a2-4f52-b984-41242e95052f";
    private static final String NON_EXISTING_ID = "/non-existing";

    /*
    Testing existing /chargers with existing ID and validating some of the response data with different types
    */
    @Test
    public void testChargersApi_Returns_200() {
        RestAssured.given(SPECIFICATION)
                .get(PATH + EXISTING_ID)
                .then()
                .statusCode(SC_OK)
                .time(lessThan(DEFAULT_REQUEST_TIMEOUT))
                .assertThat()
                .body("name", equalTo("CS-TEST-i-0e227f064eb666df2-359"))
                .and().body("type", equalTo("Level 2"))
                .and().body("model", equalTo("Lite-On IC3"))
                .and().body("ports[0].connectorTypes[0]", equalTo("J1772"))
                .and().body("ports[0].maxPowerKilowatts", equalTo(7.7f))
                .and().body("currentPrice.pricingType", equalTo("FREE"))
                .and().body("contactInfo", equalTo("+18006360986"))
                .and().body("maintenanceFlag", equalTo(false));
    }

    /*
    Covering the case when ID is invalid and such /chargers record is not present
    */
    @Test
    public void testChargersApi_Returns_404() {
        RestAssured.given(SPECIFICATION)
                .get(PATH + NON_EXISTING_ID)
                .then()
                .statusCode(SC_NOT_FOUND);
    }

}
