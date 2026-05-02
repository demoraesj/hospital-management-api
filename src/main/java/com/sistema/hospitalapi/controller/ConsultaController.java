package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.dto.ConsultaRequestDTO;
import com.sistema.hospitalapi.dto.ConsultaResponseDTO;
import com.sistema.hospitalapi.service.ConsultaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    public List<ConsultaResponseDTO> listarTodas() {
        return consultaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ConsultaResponseDTO buscarPorId(@PathVariable Long id) {
        return consultaService.buscarPorId(id);
    }

    @PostMapping
    public ConsultaResponseDTO salvar(@RequestBody ConsultaRequestDTO dto) {
        return consultaService.salvar(dto);
    }

    @PutMapping("/{id}")
    public ConsultaResponseDTO atualizar(@PathVariable Long id, @RequestBody ConsultaRequestDTO dto) {
        return consultaService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.consultaService.remover(id);
    }

}
