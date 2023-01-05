package com.elsprage.users.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private String email;
}
