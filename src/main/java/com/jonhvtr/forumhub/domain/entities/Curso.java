package com.jonhvtr.forumhub.domain.entities;

import com.jonhvtr.forumhub.domain.enums.Categoria;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}
