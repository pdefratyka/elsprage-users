package com.elsprage.users.service.impl;

import com.elsprage.users.model.request.LoginRequest;
import com.elsprage.users.model.response.LoginResponse;
import com.elsprage.users.persistance.repository.UserRepository;
import com.elsprage.users.service.AuthenticationService;
import com.elsprage.users.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return authenticate(loginRequest);
    }

    public LoginResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByLogin(request.getLogin()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }
}
