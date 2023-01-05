package com.elsprage.users.service.impl;

import com.elsprage.users.persistance.entity.UserEntity;
import com.elsprage.users.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtEncoder jwtEncoder;

    public String createBearerToken(final UserEntity userEntity) {
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .claim("user", userEntity.getLogin())
                .claim("userId", userEntity.getId())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
}
