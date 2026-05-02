package com.sistema.hospitalapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceitaRequestDTO {
    private String medicamento;
    private String dosagem;
    private Integer duracaoDias;
    private Long consultaId;
}
