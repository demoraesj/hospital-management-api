package com.sistema.hospitalapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReceitaResponseDTO {
    private Long id;
    private String medicamento;
    private String dosagem;
    private Integer duracaoDias;
    private Long consultaId;

}
