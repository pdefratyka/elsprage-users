package com.elsprage.users.service;

import com.elsprage.users.persistance.entity.UserEntity;

public interface TokenService {
    String createBearerToken(UserEntity userEntity);
}
