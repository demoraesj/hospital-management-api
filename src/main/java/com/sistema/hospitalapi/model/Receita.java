package com.sistema.hospitalapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Receita {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String medicamento;
    private String dosagem;
    private Integer duracaoDias;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

}
