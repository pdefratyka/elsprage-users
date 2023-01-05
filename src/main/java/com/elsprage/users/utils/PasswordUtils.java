package com.elsprage.users.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class PasswordUtils {
    private static final String HASHING_ALGORITHM = "SHA-256";

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(HASHING_ALGORITHM);
            byte[] passwordHash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return new String(passwordHash, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
