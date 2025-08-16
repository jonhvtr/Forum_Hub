package com.jonhvtr.forumhub.dto;

import com.jonhvtr.forumhub.domain.enums.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoRequest(
        @NotBlank(message = "{nome.obrigatorio}") String titulo,
        @NotBlank(message = "{mensagem.obrigatorio}") String mensagem,
        @NotBlank(message = "{nomeCurso.obrigatorio}") String nomeCurso,
        @NotNull(message = "{categoria.obrigatorio}") Categoria categoria
) {
}
