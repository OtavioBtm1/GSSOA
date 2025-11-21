package com.fiap.checkpoint1.dto;
import com.fiap.checkpoint1.enums.Role;

public record UsuarioCreateDTO(String nome, String email, String senha, Role role) {}
