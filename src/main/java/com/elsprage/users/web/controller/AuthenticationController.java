package com.elsprage.users.web.controller;

import com.elsprage.users.model.request.LoginRequest;
import com.elsprage.users.model.response.LoginResponse;
import com.elsprage.users.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@AllArgsConstructor
@Log4j2
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("Attempt to login with user's login: " + loginRequest.getLogin());
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
}
