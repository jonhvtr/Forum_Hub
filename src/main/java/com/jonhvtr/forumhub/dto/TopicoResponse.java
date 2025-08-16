package com.jonhvtr.forumhub.dto;

import com.jonhvtr.forumhub.domain.enums.Status;
import com.jonhvtr.forumhub.domain.entities.Topico;

import java.time.LocalDateTime;

public record TopicoResponse(Long id, String titulo, String mensagem, String autor, String curso, LocalDateTime createdAt, Status status) {
    public TopicoResponse(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getAutor().getNome(), topico.getCurso().getNome(), topico.getCreatedAt(),
                topico.getStatus());
    }
}
