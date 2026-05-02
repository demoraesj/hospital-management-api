package com.sistema.hospitalapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoRequestDTO {
    @NotBlank(message = "Nome é obrigatório)")
    private String nome;
    private String especialidade;
    @NotBlank(message = "CRM é obrigatório")
    private String crm;
}
