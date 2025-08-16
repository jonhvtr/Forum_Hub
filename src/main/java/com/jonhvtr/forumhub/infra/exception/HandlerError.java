package com.jonhvtr.forumhub.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerError {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handlerErrorNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerErrorBadRequest(MethodArgumentNotValidException exception) {
        var error = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(DataValidationError::new).toList());
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<?> handlerErrorBadRequest(HttpMessageNotWritableException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handlerErrorBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handlerError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha de autenticação");
    }

    @ExceptionHandler(AccessException.class)
    public ResponseEntity<?> handlerErrorAccessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerErrorServerError(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + exception.getLocalizedMessage());
    }

    @ExceptionHandler(ForumException.class)
    public ResponseEntity<?> handlerErrorBusinessRoles(ForumException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    private record DataValidationError(String field, String message) {
        DataValidationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
