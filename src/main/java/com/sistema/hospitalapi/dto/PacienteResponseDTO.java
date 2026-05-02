package com.sistema.hospitalapi.dto; //dados pra ver na API

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PacienteResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
}
