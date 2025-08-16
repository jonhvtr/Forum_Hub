package com.jonhvtr.forumhub.domain.entities;

import com.jonhvtr.forumhub.domain.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName role;
}
