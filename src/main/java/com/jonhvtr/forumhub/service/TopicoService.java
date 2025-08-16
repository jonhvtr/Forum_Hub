package com.jonhvtr.forumhub.service;

import com.jonhvtr.forumhub.domain.entities.Curso;
import com.jonhvtr.forumhub.domain.entities.Topico;
import com.jonhvtr.forumhub.domain.entities.User;
import com.jonhvtr.forumhub.dto.TopicoRequest;
import com.jonhvtr.forumhub.dto.TopicoResponse;
import com.jonhvtr.forumhub.dto.TopicoUpdateRequest;
import com.jonhvtr.forumhub.repository.CursoRepository;
import com.jonhvtr.forumhub.repository.TopicoRepository;
import com.jonhvtr.forumhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UserRepository userRepository;

    public TopicoResponse createTopico(TopicoRequest dados) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User usuario = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Precisa estar autenticado"));
        Curso curso = cursoRepository.findByNomeAndCategoria(dados.nomeCurso(), dados.categoria()).orElseThrow(() -> new RuntimeException("Curso não " +
                "encontrado"));

        var content = topicoRepository.findByTituloContainingIgnoreCaseAndMensagemContainingIgnoreCase(dados.titulo(), dados.mensagem());
        if (content.isPresent()) {
            throw new RuntimeException("Esse tópico já existe");
        }

        var topico = new Topico(dados, curso, usuario);
        topicoRepository.save(topico);
        return new TopicoResponse(topico);
    }

    public Page<TopicoResponse> findTopicos(Pageable pageable) {
        return topicoRepository.findAll(pageable).map(TopicoResponse::new);
    }

    public TopicoResponse findTopicoById(Long id) {
        var topico = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        return new TopicoResponse(topico);
    }

    public List<TopicoResponse> findTopicosByCourse(String nomeCurso) {
        List<Topico> topicos = topicoRepository.findByCourseName(nomeCurso);
        return topicos.stream().map(TopicoResponse::new).toList();
    }

    public TopicoResponse updateTopico(TopicoUpdateRequest dados) {
        var topico = topicoRepository.findById(dados.id()).orElseThrow(() -> new RuntimeException("Tópico não encontrado!"));
        topico.updateInformation(dados);
        return new TopicoResponse(topico);
    }

    public void deleteTopico(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        topicoRepository.delete(topico);
    }

}
