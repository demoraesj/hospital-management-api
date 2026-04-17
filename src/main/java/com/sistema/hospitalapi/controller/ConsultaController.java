package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.model.Consulta;
import com.sistema.hospitalapi.service.ConsultaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    private ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    public List<Consulta> listarTodas() {
        return consultaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Consulta buscarPorId(@PathVariable Long id) {
        return consultaService.buscarPorId(id);
    }

    @PostMapping
    public Consulta salvar(@RequestBody Consulta consulta) {
        return consultaService.salvar(consulta);
    }

    @PutMapping("/{id}")
    public Consulta atualizar(@PathVariable Long id, @RequestBody Consulta consulta) {
        return consultaService.atualizar(id, consulta);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.consultaService.remover(id);
    }

}
