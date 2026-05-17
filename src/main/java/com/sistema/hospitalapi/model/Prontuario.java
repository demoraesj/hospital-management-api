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
public class Prontuario {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String tipoSanguineo;
    private String alergia;
    private String observacoes;

    @JsonIgnore
    //@JsonBackReference("paciente-prontuario")
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

}
