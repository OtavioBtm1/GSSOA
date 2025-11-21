package com.fiap.checkpoint1.factory;

import com.fiap.checkpoint1.dto.UsuarioCreateDTO;
import com.fiap.checkpoint1.enums.Role;
import com.fiap.checkpoint1.model.Usuario;

public class UsuarioFactory {

    public static Usuario criarUsuario(UsuarioCreateDTO dto, String senhaCriptografada) {

        return new Usuario(
                dto.nome(),
                dto.email(),
                senhaCriptografada,
                dto.role() != null ? dto.role() : Role.USER
        );
    }
}
