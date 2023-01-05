package com.elsprage.users.common.mapper;

import com.elsprage.users.model.dto.UserDTO;
import com.elsprage.users.model.request.UserRequest;
import com.elsprage.users.persistance.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserEntity mapToUserEntity(final UserRequest userRequest) {
        return UserEntity.builder()
                .login(userRequest.getLogin())
                .password(userRequest.getPassword().getBytes())
                .email(userRequest.getEmail())
                .build();
    }

    public UserDTO mapToUserDTO(final UserEntity userEntity) {
        return UserDTO.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .login(userEntity.getLogin())
                .build();
    }
}
