package com.jonhvtr.forumhub.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jonhvtr.forumhub.dto.TopicoRequest;
import com.jonhvtr.forumhub.dto.TopicoUpdateRequest;
import com.jonhvtr.forumhub.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;

    @Column(name = "created_at")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private User autor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico(TopicoRequest dados, Curso curso, User autor) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.curso = curso;
        this.autor = autor;
        this.status = Status.NAO_RESPONDIDO;
    }

    public void updateInformation(TopicoUpdateRequest data) {
        if (titulo != null) {
            this.titulo = data.titulo();
        }

        if (mensagem != null) {
            this.mensagem = data.mensagem();
        }

        if (status != null) {
            this.status = data.status();
        } else {
            this.status = Status.NAO_RESPONDIDO;
        }
    }

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
