package com.sistema.hospitalapi.repository;

import com.sistema.hospitalapi.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
