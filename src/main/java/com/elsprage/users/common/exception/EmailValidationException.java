package com.elsprage.users.common.exception;

import com.elsprage.users.utils.UserValidator;

public class EmailValidationException extends UserValidationException {

    public EmailValidationException(String message) {
        super(message);
    }

    public static class EmailAlreadyExistsException extends EmailValidationException {
        public EmailAlreadyExistsException(String email) {
            super("User with email: " + email + " already exists");
        }
    }

    public static class EmailIsTooShortException extends EmailValidationException {
        public EmailIsTooShortException(String email) {
            super("Email: " + email + " is too short. It has to contain at least:" + UserValidator.MIN_EMAIl_LENGTH + " characters.");
        }
    }

    public static class EmailIsTooLongException extends EmailValidationException {
        public EmailIsTooLongException(String email) {
            super("Email: " + email + " is too long. It can contain max: " + UserValidator.MAX_EMAIl_LENGTH + " characters.");
        }
    }

    public static class EmailIsNotValid extends EmailValidationException {
        public EmailIsNotValid(String email) {
            super("Email: " + email + " is not valid.");
        }
    }
}
