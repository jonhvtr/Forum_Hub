package com.jonhvtr.forumhub.service;

import com.jonhvtr.forumhub.domain.entities.Role;
import com.jonhvtr.forumhub.domain.entities.User;
import com.jonhvtr.forumhub.domain.UserDetailsImpl;
import com.jonhvtr.forumhub.dto.CreateUserDTO;
import com.jonhvtr.forumhub.dto.LoginUserDTO;
import com.jonhvtr.forumhub.infra.security.SecurityConfigurations;
import com.jonhvtr.forumhub.infra.security.TokenService;
import com.jonhvtr.forumhub.infra.security.dto.DataTokenJWT;
import com.jonhvtr.forumhub.repository.RoleRepository;
import com.jonhvtr.forumhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityConfigurations securityConfigurations;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleRepository roleRepository;

    public DataTokenJWT authenticateUser(LoginUserDTO loginUserDTO) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserDTO.email(), loginUserDTO.senha());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new DataTokenJWT(tokenService.generateToken(userDetails));
    }


    public void createUser(CreateUserDTO createUserDTO) {
        Role role = roleRepository.findByRole(createUserDTO.role()).orElseThrow(() -> new RuntimeException("Role não encontrado"));

        User newUser = User.builder()
                .nome(createUserDTO.nome())
                .email(createUserDTO.email())
                .senha(securityConfigurations.passwordEncoder().encode(createUserDTO.senha()))
                .role(List.of(role))
                .build();

        userRepository.save(newUser);
    }
}

