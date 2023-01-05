package com.elsprage.users.service.impl;

import com.elsprage.users.common.exception.WrongCredentialsException;
import com.elsprage.users.model.request.LoginRequest;
import com.elsprage.users.model.response.LoginResponse;
import com.elsprage.users.persistance.entity.UserEntity;
import com.elsprage.users.persistance.repository.UserRepository;
import com.elsprage.users.service.AuthenticationService;
import com.elsprage.users.service.TokenService;
import com.elsprage.users.utils.PasswordUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<UserEntity> userEntity = userRepository.findByLogin(loginRequest.getLogin());
        if (userEntity.isPresent() && isPasswordMatch(loginRequest.getPassword(), userEntity.get().getPassword())) {
            return new LoginResponse(tokenService.createBearerToken(userEntity.get()));
        }
        throw new WrongCredentialsException();
    }

    private boolean isPasswordMatch(String requestPassword, byte[] passwordFromDatabase) {
        String hashedRequestPassword = PasswordUtils.hashPassword(requestPassword);
        String passwordFromDbAsString = new String(passwordFromDatabase);
        return passwordFromDbAsString.equals(hashedRequestPassword);
    }
}
