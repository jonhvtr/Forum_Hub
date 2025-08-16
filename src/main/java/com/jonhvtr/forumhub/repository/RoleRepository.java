package com.jonhvtr.forumhub.repository;

import com.jonhvtr.forumhub.domain.entities.Role;
import com.jonhvtr.forumhub.domain.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(RoleName roleName);
}
