package com.elsprage.users.service;

import com.elsprage.users.model.dto.UserDTO;
import com.elsprage.users.model.request.UserRequest;

public interface UserService {
    UserDTO createUser(UserRequest userRequest);
}
