package com.sistema.hospitalapi.exception;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiErrorDTO {

    private List<String> errors;

    public ApiErrorDTO(String mensagem) {
        this.errors = Arrays.asList(mensagem);
    }

    public ApiErrorDTO(List<String> errors) {
        this.errors = errors;
    }
}
