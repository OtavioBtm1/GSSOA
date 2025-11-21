package com.fiap.checkpoint1.service;

import com.fiap.checkpoint1.model.Usuario;
import com.fiap.checkpoint1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Importante para BCrypt
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;  

     @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

     
    public Usuario salvar(Usuario usuario) {
         String senhaHash = passwordEncoder.encode(usuario.getSenha());
        
         usuario.setSenha(senhaHash); 
        
         return usuarioRepository.save(usuario);
    }

    
    public boolean autenticar(String email, String senhaPlana) { 
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        
         if (usuarioOpt.isEmpty()) { 
            return false; 
        }
        
        Usuario usuario = usuarioOpt.get();
        
         return passwordEncoder.matches(senhaPlana, usuario.getSenha());
    }

 
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}