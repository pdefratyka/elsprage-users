package com.elsprage.users.web.controller;

import com.elsprage.users.AbstractIT;
import com.elsprage.users.model.request.UserRequest;
import com.elsprage.users.persistance.entity.UserEntity;
import com.elsprage.users.persistance.repository.UserRepository;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTestIT extends AbstractIT {

    private static final String URL = "/users";

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository.deleteAll();
    }

    @Test
    void shouldCreateUser() {
        String login = "login";
        String password = "password";
        String email = "email@email.pl";
        UserRequest userRequest = getUserRequest(login, email, password);
        requestSpecification.body(userRequest);

        given(requestSpecification)
                .when()
                .post(URL)
                .then()
                .statusCode(200)
                .body("login", equalTo(login))
                .body("email", equalTo(email))
                .log().ifValidationFails(LogDetail.ALL);

        Optional<UserEntity> userEntity = userRepository.findByLogin(login);
        assertTrue(userEntity.isPresent());
    }

    @Test
    void shouldThrowExceptionCauseWrongEmail() {
        String login = "login";
        String password = "password";
        String invalidEmail = "email";
        UserRequest userRequest = getUserRequest(login, invalidEmail, password);
        requestSpecification.body(userRequest);

        given(requestSpecification)
                .when()
                .post(URL)
                .then()
                .statusCode(400)
                .log().ifValidationFails(LogDetail.ALL);

        Optional<UserEntity> userEntity = userRepository.findByLogin(login);
        assertFalse(userEntity.isPresent());
    }

    @Test
    void shouldThrowExceptionCauseLoginAlreadyExists() {
        String existingLogin = "login";
        String password = "password";
        String email = "someEmail2@em.pl";
        UserRequest userRequest = getUserRequest(existingLogin, email, password);
        requestSpecification.body(userRequest);

        given(requestSpecification)
                .when()
                .post(URL)
                .then()
                .statusCode(200)
                .log().ifValidationFails(LogDetail.ALL);

        given(requestSpecification)
                .when()
                .post(URL)
                .then()
                .statusCode(400)
                .log().ifValidationFails(LogDetail.ALL);

        Optional<UserEntity> userEntity = userRepository.findByLogin(existingLogin);
        int usersSize = userRepository.findAll().size();
        assertTrue(userEntity.isPresent());
        assertEquals(1, usersSize);
    }

    private UserRequest getUserRequest(String login, String email, String password) {
        return UserRequest.builder()
                .login(login)
                .password(password)
                .email(email)
                .build();
    }
}
