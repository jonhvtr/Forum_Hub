package com.jonhvtr.forumhub.controller;

import com.jonhvtr.forumhub.dto.CreateUserDTO;
import com.jonhvtr.forumhub.dto.LoginUserDTO;
import com.jonhvtr.forumhub.dto.UserResponse;
import com.jonhvtr.forumhub.infra.security.dto.DataTokenJWT;
import com.jonhvtr.forumhub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginUserDTO loginUserDTO) {
        DataTokenJWT tokenJWT = userService.authenticateUser(loginUserDTO);
        return new ResponseEntity<>(tokenJWT, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid CreateUserDTO createUserDTO) {
        userService.createUser(createUserDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
