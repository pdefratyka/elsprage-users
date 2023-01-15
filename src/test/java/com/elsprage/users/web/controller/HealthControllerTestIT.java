package com.elsprage.users.web.controller;

import com.elsprage.users.AbstractIT;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HealthControllerTestIT extends AbstractIT {

    @Test
    void healthCheckShouldReturn200() {
        given(requestSpecification)
                .when()
                .get("/actuator/health")
                .then()
                .statusCode(200)
                .log().ifValidationFails(LogDetail.ALL);
    }
}
