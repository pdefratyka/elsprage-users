package com.elsprage.users.common.exception;

import com.elsprage.users.utils.UserValidator;

public class PasswordValidationException extends UserValidationException {
    public PasswordValidationException(String message) {
        super(message);
    }

    public static class PasswordIsTooShortException extends PasswordValidationException {
        public PasswordIsTooShortException() {
            super("Password is to short. It has to contain at least: " + UserValidator.MIN_PASSWORD_LENGTH + " characters");
        }
    }

    public static class PasswordIsTooLongException extends PasswordValidationException {
        public PasswordIsTooLongException() {
            super("Password is too long. It can contain only: " + UserValidator.MAX_PASSWORD_LENGTH + " characters.");
        }
    }
}
