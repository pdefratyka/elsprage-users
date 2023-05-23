package com.elsprage.users.web.controller;

import com.elsprage.users.model.dto.UserDTO;
import com.elsprage.users.model.request.UserRequest;
import com.elsprage.users.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRequest userRequest) {
        log.info("Create user with login: " + userRequest.getLogin());
        return ResponseEntity.ok(userService.createUser(userRequest));
    }
}
