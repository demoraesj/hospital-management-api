package com.sistema.hospitalapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ConsultaResponseDTO {
    private Long id;
    private LocalDateTime dataHora;
    private String pacienteNome;
    private String medicoNome;
    private String motivo;
}
