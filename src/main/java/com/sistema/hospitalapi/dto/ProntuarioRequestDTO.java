package com.sistema.hospitalapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProntuarioRequestDTO {
    private String tipoSanguineo;
    private String alergia;
    private String observacoes;
    private Long pacienteId;
}
