package com.elsprage.users.common.exception;

import com.elsprage.users.utils.UserValidator;

public class LoginValidationException extends UserValidationException {
    public LoginValidationException(String message) {
        super(message);
    }

    public static class LoginAlreadyExistsException extends LoginValidationException {

        public LoginAlreadyExistsException(String login) {
            super("User with login: " + login + " already exists");
        }
    }

    public static class LoginIsTooShortException extends LoginValidationException {
        public LoginIsTooShortException(String login) {
            super("Login: " + login + " is too short. It has to contain at least:" + UserValidator.MIN_LOGIN_LENGTH + " characters.");
        }
    }

    public static class LoginIsTooLongException extends LoginValidationException {
        public LoginIsTooLongException(String login) {
            super("Login: " + login + " is too long. It can contain max: " + UserValidator.MAX_LOGIN_LENGTH + " characters.");
        }
    }

    public static class LoginIsNotValid extends LoginValidationException {
        public LoginIsNotValid(String login) {
            super("Login: " + login + " is not valid.");
        }
    }
}
