package com.jonhvtr.forumhub.dto;

import com.jonhvtr.forumhub.domain.enums.RoleName;

public record CreateUserDTO(String nome, String email, String senha, RoleName role) {
}
