package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.model.Convenio;
import com.sistema.hospitalapi.service.ConvenioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ConvenioController {
    private ConvenioService convenioService;

    public ConvenioController(ConvenioService convenioService) {
        this.convenioService = convenioService;
    }

    @GetMapping
    public List<Convenio> listarTodas(){
        return convenioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Convenio buscarPorId(@PathVariable Long id){
        return convenioService.buscarPorId(id);
    }

    @PostMapping
    public Convenio salvar(@RequestBody Convenio convenio) {
        return convenioService.salvar(convenio);
    }

    @PutMapping("/{id}")
    public Convenio atualizar(@PathVariable Long id, @RequestBody Convenio convenio) {
        return convenioService.atualizar(id, convenio);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.convenioService.remover(id);
    }

}
