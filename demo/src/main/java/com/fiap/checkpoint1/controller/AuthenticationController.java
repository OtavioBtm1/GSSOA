package com.fiap.checkpoint1.controller;

import com.fiap.checkpoint1.dto.LoginRequest;
import com.fiap.checkpoint1.model.Usuario;
import com.fiap.checkpoint1.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")  
public class AuthenticationController {

     @Autowired
    private AuthenticationManager authenticationManager;

     @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest data) {
        
         UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(data.email(), data.senha());

       
        Authentication authentication = authenticationManager.authenticate(authToken);

          Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = tokenService.generateToken(usuario);

         return ResponseEntity.ok(token);
    }
}