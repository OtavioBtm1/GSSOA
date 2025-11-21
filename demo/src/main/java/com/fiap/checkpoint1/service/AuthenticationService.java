package com.fiap.checkpoint1.service;

import com.fiap.checkpoint1.enums.Role;
import com.fiap.checkpoint1.model.Usuario;
import com.fiap.checkpoint1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }

    /** 
     * Cadastrar novo usuário com senha encriptada e Role padrão 
     */
    public Usuario register(String nome, String email, String senha) {
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email já cadastrado!");
        }

        Usuario user = new Usuario(
                nome,
                email,
                passwordEncoder.encode(senha),
                Role.USER // define role padrão
        );

        return usuarioRepository.save(user);
    }
}
