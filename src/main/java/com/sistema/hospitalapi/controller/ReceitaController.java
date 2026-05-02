package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.dto.ReceitaRequestDTO;
import com.sistema.hospitalapi.dto.ReceitaResponseDTO;
import com.sistema.hospitalapi.service.ReceitaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receita")
public class ReceitaController {
    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping
    public List<ReceitaResponseDTO> listarTodas() {
        return receitaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ReceitaResponseDTO buscarPorId(@PathVariable Long id) {
        return receitaService.buscarPorId(id);
    }

    @PostMapping
    public ReceitaResponseDTO salvar(@RequestBody ReceitaRequestDTO dto) {
        return receitaService.salvar(dto);
    }

    @PutMapping("/{id}")
    public ReceitaResponseDTO atualizar(@PathVariable Long id, @RequestBody ReceitaRequestDTO dto) {
        return receitaService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.receitaService.remover(id);
    }
}
