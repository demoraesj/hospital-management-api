package com.sistema.hospitalapi.repository;

import com.sistema.hospitalapi.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}
