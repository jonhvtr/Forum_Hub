package com.jonhvtr.forumhub.dto;

import com.jonhvtr.forumhub.domain.entities.User;

public record UserResponse(Long id, String nome, String login) {
    public UserResponse(User user) {
        this(user.getId(), user.getNome(), user.getEmail());
    }
}
