package com.elsprage.users.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}
