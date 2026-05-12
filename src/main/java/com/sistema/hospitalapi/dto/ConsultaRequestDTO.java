package com.sistema.hospitalapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.aspectj.bridge.IMessage;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultaRequestDTO {

    @NotNull(message = "Data é obrigatória")
    private LocalDateTime dataHora;

    @NotNull(message = "Paciente é obrigatório")
    private Long pacienteId;

    @NotNull(message = "Médico é obrigatório")
    private Long medicoId;

    @NotBlank(message = "Motivo é obrigatório")
    private String motivo;

    @NotNull(message = "Valor é obrigatório")
    private Double valor;
}
