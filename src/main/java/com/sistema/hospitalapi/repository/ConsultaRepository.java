package com.sistema.hospitalapi.repository;

import com.sistema.hospitalapi.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
