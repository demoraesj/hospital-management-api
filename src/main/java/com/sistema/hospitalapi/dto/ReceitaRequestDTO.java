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
public class ReceitaRequestDTO {

    @NotBlank(message = "Medicamento é obrigatório")
    private String medicamento;

    @NotBlank(message = "Dosagem é obrigatório")
    private String dosagem;

    @NotNull(message = "Duração é obrigatória")
    private Integer duracaoDias;

    @NotNull(message = "Consulta é obrigatória")
    private Long consultaId;
}
