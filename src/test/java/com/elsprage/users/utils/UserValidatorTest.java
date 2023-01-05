package com.elsprage.users.utils;

import com.elsprage.users.common.exception.EmailValidationException;
import com.elsprage.users.common.exception.LoginValidationException;
import com.elsprage.users.common.exception.PasswordValidationException;
import com.elsprage.users.model.request.UserRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserValidatorTest {

    @Test
    void shouldValidateUserRequest() {
        UserRequest userRequest = new UserRequest("validLogin", "validPassword", "valid@email.com");
        UserValidator.isUserRequestValid(userRequest);
    }

    @Test
    void shouldThrowExceptionWhenLoginIsNotValid() {
        UserRequest userRequest = new UserRequest("inv", "validPassword", "valid@email.com");
        assertThrows(LoginValidationException.LoginIsTooShortException.class, () -> UserValidator.isUserRequestValid(userRequest));
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNotValid() {
        UserRequest userRequest = new UserRequest("validLogin", "", "valid@email.com");
        assertThrows(PasswordValidationException.PasswordIsTooShortException.class, () -> UserValidator.isUserRequestValid(userRequest));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNotValid() {
        UserRequest userRequest = new UserRequest("validLogin", "validPassword", "invalidEmail");
        assertThrows(EmailValidationException.EmailIsNotValid.class, () -> UserValidator.isUserRequestValid(userRequest));
    }
}