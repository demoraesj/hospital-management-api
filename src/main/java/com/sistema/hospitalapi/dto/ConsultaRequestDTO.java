package com.sistema.hospitalapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultaRequestDTO {
    private LocalDateTime dataHora;
    private Long pacienteId;
    private Long medicoId;
    private String motivo;
    private Double valor;
}
