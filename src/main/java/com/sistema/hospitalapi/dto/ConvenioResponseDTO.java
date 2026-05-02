package com.sistema.hospitalapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConvenioResponseDTO {
    private Long id;
    private String nome;
    private String cnpj;
}
