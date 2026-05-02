package com.sistema.hospitalapi.repository;

import com.sistema.hospitalapi.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}
