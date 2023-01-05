package com.elsprage.users.persistance.repository;

import com.elsprage.users.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailOrLogin(String email, String login);

    Optional<UserEntity> findByLogin(String login);
}
