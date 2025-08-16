package com.jonhvtr.forumhub.repository;

import com.jonhvtr.forumhub.domain.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloContainingIgnoreCaseAndMensagemContainingIgnoreCase(String titulo, String mensagem);

    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
    List<Topico> findByCourseName(@Param("nomeCurso") String nomeCurso);
}
