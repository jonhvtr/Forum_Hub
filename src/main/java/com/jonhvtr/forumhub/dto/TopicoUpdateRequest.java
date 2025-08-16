package com.jonhvtr.forumhub.dto;

import com.jonhvtr.forumhub.domain.enums.Status;

public record TopicoUpdateRequest(
        Long id,
        String titulo,
        String mensagem,
        Status status
) {
}
