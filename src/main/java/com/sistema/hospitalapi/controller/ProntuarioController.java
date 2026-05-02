package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.dto.ProntuarioRequestDTO;
import com.sistema.hospitalapi.dto.ProntuarioResponseDTO;
import com.sistema.hospitalapi.service.ProntuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {
    private final ProntuarioService prontuarioService;

    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }

    @GetMapping
    public List<ProntuarioResponseDTO> listarTodas() {
        return prontuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ProntuarioResponseDTO buscarPorId(@PathVariable Long id) {
        return prontuarioService.buscarPorId(id);
    }

    @PostMapping
    public ProntuarioResponseDTO salvar(@RequestBody ProntuarioRequestDTO dto) {
        return prontuarioService.salvar(dto);
    }

    @PutMapping("/{id}")
    public ProntuarioResponseDTO atualizar(@PathVariable Long id, @RequestBody ProntuarioRequestDTO dto) {
        return prontuarioService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.prontuarioService.remover(id);
    }
}
