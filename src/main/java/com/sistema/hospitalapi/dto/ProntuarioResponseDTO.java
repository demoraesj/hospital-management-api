package com.sistema.hospitalapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProntuarioResponseDTO {
    private Long id;
    private String tipoSanguineo;
    private String alergia;
    private String observacoes;
    private String pacienteNome;

}
