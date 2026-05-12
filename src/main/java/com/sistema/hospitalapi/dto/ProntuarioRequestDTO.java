package com.sistema.hospitalapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProntuarioRequestDTO {

    @NotBlank(message = "Tipo sanguineo é obrigatório")
    private String tipoSanguineo;

    @NotBlank(message = "Alergia é obrigatória")
    private String alergia;

    @NotBlank(message = "Observações é obrigatório")
    private String observacoes;

    @NotNull(message = "Paciente é obrigatório")
    private Long pacienteId;
}
