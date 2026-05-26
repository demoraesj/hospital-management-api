package com.sistema.hospitalapi.dto.usuario;

import com.sistema.hospitalapi.enums.Perfil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private Perfil perfil;
}
