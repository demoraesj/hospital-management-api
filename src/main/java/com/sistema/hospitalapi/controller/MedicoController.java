package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.model.Medico;
import com.sistema.hospitalapi.service.MedicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MedicoController {
    private MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public List<Medico> listarTodas() {
        return medicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Medico buscarPorId(@PathVariable Long id) {
        return medicoService.buscarPorId(id);
    }

    @PostMapping
    public Medico salvar(@RequestBody Medico medico) {
        return medicoService.salvar(medico);
    }

    @PutMapping("/{id}")
    public Medico atualizar(@PathVariable Long id, @RequestBody Medico medico) {
        return medicoService.atualizar(id, medico);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.medicoService.remover(id);
    }
}
