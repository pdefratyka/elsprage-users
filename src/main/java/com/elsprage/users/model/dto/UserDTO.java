package com.elsprage.users.model.dto;

import lombok.Builder;

@Builder
public record UserDTO(Long id, String login, String email) {
}
