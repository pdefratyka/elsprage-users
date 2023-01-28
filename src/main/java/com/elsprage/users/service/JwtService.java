package com.elsprage.users.service;

import com.elsprage.users.persistance.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String extractUsername(String jwt);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(UserEntity userEntity);

    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
