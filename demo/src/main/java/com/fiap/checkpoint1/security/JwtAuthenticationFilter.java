package com.fiap.checkpoint1.security;

import com.fiap.checkpoint1.repository.UsuarioRepository;
import com.fiap.checkpoint1.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public JwtAuthenticationFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws java.io.IOException, jakarta.servlet.ServletException {

        String token = getToken(request);

        if (token != null) {

            String email = tokenService.getSubject(token);
            String role = tokenService.getRole(token);

            var usuario = usuarioRepository.findByEmail(email);

            if (usuario.isPresent()) {
                var auth = new UsernamePasswordAuthenticationToken(
                        usuario.get(),
                        null,
                        java.util.List.of(() -> "ROLE_" + role) // <<< PERMISSÃO DO TOKEN
                );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer "))
            return header.substring(7);

        return null;
    }
}
