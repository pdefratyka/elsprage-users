package com.elsprage.users.service;

import com.elsprage.users.model.request.LoginRequest;
import com.elsprage.users.model.response.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest loginRequest);
}
