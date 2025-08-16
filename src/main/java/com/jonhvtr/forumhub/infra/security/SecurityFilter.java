package com.jonhvtr.forumhub.infra.security;

import com.jonhvtr.forumhub.domain.entities.User;
import com.jonhvtr.forumhub.domain.UserDetailsImpl;
import com.jonhvtr.forumhub.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (checkIfEndpointIsNotPublic(request)) {
            String token = recoveryToken(request);
            if (token != null) {
                String subject = tokenService.getSubject(token);
                User user = userRepository.findByEmail(subject).get();
                UserDetailsImpl userDetails = new UserDetailsImpl(user);

                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token ausente ou inválido");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return Arrays.stream(SecurityConfigurations.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).noneMatch(pattern ->
                pathMatcher.match(pattern, requestURI));
    }
}
