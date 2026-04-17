package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.model.Paciente;
import com.sistema.hospitalapi.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> listarTodos() {
        return pacienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Paciente buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    @PostMapping
    public Paciente salvar(@RequestBody Paciente paciente) {
        return pacienteService.salvar(paciente);
    }

    @PutMapping("/{id}")
    public Paciente atualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        return pacienteService.atualizar(id, paciente);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.pacienteService.remover(id);
    }
}
