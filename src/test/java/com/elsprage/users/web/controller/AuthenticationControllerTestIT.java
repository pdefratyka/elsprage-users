package com.elsprage.users.web.controller;

import com.elsprage.users.AbstractIT;
import com.elsprage.users.model.request.LoginRequest;
import com.elsprage.users.persistance.enumeration.Role;
import com.elsprage.users.persistance.entity.UserEntity;
import com.elsprage.users.persistance.repository.UserRepository;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthenticationControllerTestIT extends AbstractIT {

    private static final String URL = "/authentication";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void beforeEach() {
        userRepository.deleteAll();
    }

    @Test
    void shouldLogin() {
        String login = "login";
        String password = "password";
        LoginRequest loginRequest = LoginRequest.builder()
                .login(login)
                .password(password)
                .build();
        saveUserToDatabase(login, password);
        requestSpecification.body(loginRequest);

        given(requestSpecification)
                .when()
                .post(URL + "/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .log().ifValidationFails(LogDetail.ALL);
    }

    // TODO In this scenario endpoint should return 401
    @Test
    void shouldReturn403CauseBadCredentials() {
        String login = "login";
        String password = "password";
        LoginRequest loginRequest = LoginRequest.builder()
                .login(login)
                .password(password)
                .build();
        requestSpecification.body(loginRequest);

        given(requestSpecification)
                .when()
                .post(URL + "/login")
                .then()
                .statusCode(401)
                .log().ifValidationFails(LogDetail.ALL);
    }

    // TODO In this scenario endpoint should return 401
    @Test
    void shouldReturn403CausePasswordDoesNotMatch() {
        String login = "login";
        String password = "password";
        LoginRequest loginRequest = LoginRequest.builder()
                .login(login)
                .password("otherPassword")
                .build();
        saveUserToDatabase(login, password);
        requestSpecification.body(loginRequest);

        given(requestSpecification)
                .when()
                .post(URL + "/login")
                .then()
                .statusCode(401)
                .log().ifValidationFails(LogDetail.ALL);
    }

    private void saveUserToDatabase(String login, String password) {
        UserEntity userEntity = UserEntity.builder()
                .login(login)
                .password(passwordEncoder.encode(password))
                .email("someEmail@em.pl")
                .role(Role.USER)
                .build();
        userRepository.save(userEntity);
    }
}
