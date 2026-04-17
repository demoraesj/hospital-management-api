package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.model.Receita;
import com.sistema.hospitalapi.service.ReceitaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ReceitaController {
    private ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping
    public List<Receita> listarTodas() {
        return receitaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Receita buscarPorId(@PathVariable Long id) {
        return receitaService.buscarPorId(id);
    }

    @PostMapping
    public Receita salvar(@RequestBody Receita receita) {
        return receitaService.salvar(receita);
    }

    @PutMapping("/{id}")
    public Receita atualizar(@PathVariable Long id, @RequestBody Receita receita) {
        return receitaService.atualizar(id, receita);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.receitaService.remover(id);
    }
}
