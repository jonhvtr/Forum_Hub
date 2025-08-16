package com.jonhvtr.forumhub.controller;

import com.jonhvtr.forumhub.dto.TopicoRequest;
import com.jonhvtr.forumhub.dto.TopicoResponse;
import com.jonhvtr.forumhub.dto.TopicoUpdateRequest;
import com.jonhvtr.forumhub.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topico")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @PostMapping("/create")
    public ResponseEntity<TopicoResponse> createTopico(@RequestBody @Valid TopicoRequest dados, UriComponentsBuilder uriComponentsBuilder) {
        var topico = topicoService.createTopico(dados);
        var uri = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.id()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoResponse>> findTopico(@PageableDefault(size = 10, sort = {"createdAt"}) Pageable pageable) {
        var page = topicoService.findTopicos(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse> findTopicoById(@PathVariable Long id) {
        var topico = topicoService.findTopicoById(id);
        return ResponseEntity.ok(topico);
    }

    @GetMapping("/curso/{nomeCurso}")
    public ResponseEntity<List<TopicoResponse>> findTopicoByCourse(@RequestParam String nomeCurso) {
        nomeCurso = nomeCurso.replace("+", " ");
        var topico = topicoService.findTopicosByCourse(nomeCurso);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<TopicoResponse> updateTopico(@RequestBody @Valid TopicoUpdateRequest data) {
        var topico = topicoService.updateTopico(data);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteTopico(@PathVariable Long id) {
        topicoService.deleteTopico(id);
        return ResponseEntity.noContent().build();
    }
}
