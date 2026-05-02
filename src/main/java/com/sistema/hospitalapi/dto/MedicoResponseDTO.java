package com.sistema.hospitalapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicoResponseDTO {
    private Long id;
    private String nome;
    private String especialidade;
    private String crm;
}
