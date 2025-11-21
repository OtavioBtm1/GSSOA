package com.fiap.checkpoint1.controller;

import com.fiap.checkpoint1.enums.Role;
import com.fiap.checkpoint1.dto.LoginRequest;
import com.fiap.checkpoint1.dto.UsuarioCreateDTO;
import com.fiap.checkpoint1.model.Usuario;
import com.fiap.checkpoint1.repository.UsuarioRepository;
import com.fiap.checkpoint1.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    // ------------------ CADASTRO ------------------
    @PostMapping("/cadastro")
    public ResponseEntity<?> registrar(@RequestBody UsuarioCreateDTO data) {

        if (usuarioRepository.findByEmail(data.email()).isPresent()) {
            return ResponseEntity.status(409).body("Email já cadastrado.");
        }

        Usuario novoUsuario = new Usuario(
        data.nome(),
        data.email(),
        passwordEncoder.encode(data.senha()),
        Role.USER  // padrão pra novos cadastros
);


        usuarioRepository.save(novoUsuario);

        return ResponseEntity.status(201).body("Usuário criado com sucesso!");
    }



    // ------------------ LISTAR USUARIOS ------------------
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }


    // ------------------ BUSCAR POR ID ------------------
    @GetMapping("/{id}")
public ResponseEntity<?> buscar(@PathVariable Long id) {
    var usuario = usuarioRepository.findById(id);
    return usuario.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}




    // ------------------ DELETAR ------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(404).body("Usuário não encontrado");
    }
}
