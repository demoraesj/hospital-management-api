package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.model.Prontuario;
import com.sistema.hospitalapi.service.ProntuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProntuarioController {
    private ProntuarioService prontuarioService;

    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }

    @GetMapping
    public List<Prontuario> listarTodas() {
        return prontuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Prontuario buscarPorId(@PathVariable Long id) {
        return prontuarioService.buscarPorId(id);
    }

    @PostMapping
    public Prontuario salvar(@RequestBody Prontuario prontuario) {
        return prontuarioService.salvar(prontuario);
    }

    @PutMapping("/{id}")
    public Prontuario atualizar(@PathVariable Long id, @RequestBody Prontuario prontuario) {
        return prontuarioService.atualizar(id, prontuario);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.prontuarioService.remover(id);
    }
}
