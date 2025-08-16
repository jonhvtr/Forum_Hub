package com.jonhvtr.forumhub.repository;

import com.jonhvtr.forumhub.domain.enums.Categoria;
import com.jonhvtr.forumhub.domain.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNomeAndCategoria(String nome, Categoria categoria);
}
