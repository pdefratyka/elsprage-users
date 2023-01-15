package com.elsprage.users;

import com.elsprage.users.tools.testcontainers.ConsulInitializer;
import com.elsprage.users.tools.testcontainers.PostgresInitializer;
import com.elsprage.users.tools.testcontainers.TestContainerInitializer;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@DirtiesContext
@ActiveProfiles("it-test")
@SpringBootTest(classes = UsersApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"AUTH_SECRET_KEY = 763979244226452948404D6251655468576D5A7134743777217A25432A462D4A"})
public class AbstractIT {

    @LocalServerPort
    protected int localPort;

    protected RequestSpecification requestSpecification;

    static {
        TestContainerInitializer.start(
                new PostgresInitializer(),
                new ConsulInitializer());
    }

    @BeforeEach
    public void setUpAbstractIntegrationTest() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        requestSpecification = new RequestSpecBuilder()
                .setPort(localPort)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
