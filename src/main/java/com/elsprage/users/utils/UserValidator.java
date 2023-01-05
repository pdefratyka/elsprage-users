package com.elsprage.users.utils;

import com.elsprage.users.common.exception.EmailValidationException;
import com.elsprage.users.common.exception.LoginValidationException;
import com.elsprage.users.common.exception.PasswordValidationException;
import com.elsprage.users.model.request.UserRequest;
import org.springframework.util.ObjectUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    public static final int MIN_LOGIN_LENGTH = 5;
    public static final int MAX_LOGIN_LENGTH = 20;
    public static final int MIN_EMAIl_LENGTH = 5;
    public static final int MAX_EMAIl_LENGTH = 20;
    public static final int MIN_PASSWORD_LENGTH = 5;
    public static final int MAX_PASSWORD_LENGTH = 80;
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static void isUserRequestValid(final UserRequest userRequest) {
        isLoginValid(userRequest.getLogin());
        isEmailValid(userRequest.getEmail());
        isPasswordValid(userRequest.getPassword());
    }

    private static void isLoginValid(String login) {
        if (ObjectUtils.isEmpty(login) || login.length() < MIN_LOGIN_LENGTH) {
            throw new LoginValidationException.LoginIsTooShortException(login);
        } else if (login.length() > MAX_LOGIN_LENGTH) {
            throw new LoginValidationException.LoginIsTooLongException(login);
        }
    }

    private static void isEmailValid(String email) {
        if (ObjectUtils.isEmpty(email) || email.length() < MIN_EMAIl_LENGTH) {
            throw new EmailValidationException.EmailIsTooShortException(email);
        } else if (email.length() > MAX_EMAIl_LENGTH) {
            throw new EmailValidationException.EmailIsTooLongException(email);
        } else if (!isEmailMatchRegex(email)) {
            throw new EmailValidationException.EmailIsNotValid(email);
        }
    }

    private static boolean isEmailMatchRegex(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    private static void isPasswordValid(String password) {
        if (ObjectUtils.isEmpty(password) || password.length() < MIN_PASSWORD_LENGTH) {
            throw new PasswordValidationException.PasswordIsTooShortException();
        } else if (password.length() > MAX_PASSWORD_LENGTH) {
            throw new PasswordValidationException.PasswordIsTooLongException();
        }
    }
}
