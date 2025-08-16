package com.jonhvtr.forumhub.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "User")
@Table(name = "usuarios")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role;

}
