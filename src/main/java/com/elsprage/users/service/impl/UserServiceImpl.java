package com.elsprage.users.service.impl;

import com.elsprage.users.common.exception.EmailValidationException;
import com.elsprage.users.common.exception.LoginValidationException;
import com.elsprage.users.common.mapper.UserMapper;
import com.elsprage.users.model.dto.UserDTO;
import com.elsprage.users.model.request.UserRequest;
import com.elsprage.users.persistance.enumeration.Role;
import com.elsprage.users.persistance.entity.UserEntity;
import com.elsprage.users.persistance.repository.UserRepository;
import com.elsprage.users.service.UserService;
import com.elsprage.users.utils.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(final UserRequest userRequest) {
        validateUserRequest(userRequest);
        hashPassword(userRequest);
        final UserEntity userToSave = userMapper.mapToUserEntity(userRequest);
        setUserRole(userToSave);
        final UserEntity savedUser = userRepository.save(userToSave);
        log.info("Created user with login: " + savedUser.getLogin());
        return userMapper.mapToUserDTO(savedUser);
    }

    private void validateUserRequest(final UserRequest userRequest) {
        UserValidator.isUserRequestValid(userRequest);
        validateIfGivenUserDoesNotExistYet(userRequest);
    }

    private void validateIfGivenUserDoesNotExistYet(final UserRequest userRequest) {
        final Optional<UserEntity> existingUser = userRepository.findByEmailOrLogin(userRequest.getEmail(), userRequest.getLogin());
        if (existingUser.isPresent()) {
            String existingEmail = existingUser.get().getEmail();
            String existingLogin = existingUser.get().getLogin();
            if (existingEmail.equals(userRequest.getEmail())) {
                throw new EmailValidationException.EmailAlreadyExistsException(userRequest.getEmail());
            } else if (existingLogin.equals(userRequest.getLogin())) {
                throw new LoginValidationException.LoginAlreadyExistsException(existingLogin);
            }
        }
    }

    private void hashPassword(final UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    }

    private void setUserRole(final UserEntity userEntity) {
        userEntity.setRole(Role.USER);
    }
}
