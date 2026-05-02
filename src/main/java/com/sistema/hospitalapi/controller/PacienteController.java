package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.dto.PacienteRequestDTO;
import com.sistema.hospitalapi.dto.PacienteResponseDTO;
import com.sistema.hospitalapi.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<PacienteResponseDTO> listarTodos() {
        return pacienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public PacienteResponseDTO buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    @PostMapping
    public PacienteResponseDTO salvar(@RequestBody PacienteRequestDTO dto) {
        return pacienteService.salvar(dto);
    }

    @PutMapping("/{id}")
    public PacienteResponseDTO atualizar(@PathVariable Long id, @RequestBody PacienteRequestDTO dto) {
        return pacienteService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.pacienteService.remover(id);
    }
}
