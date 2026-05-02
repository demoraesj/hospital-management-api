package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.dto.MedicoRequestDTO;
import com.sistema.hospitalapi.dto.MedicoResponseDTO;
import com.sistema.hospitalapi.service.MedicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public List<MedicoResponseDTO> listarTodas() {
        return medicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public MedicoResponseDTO buscarPorId(@PathVariable Long id) {
        return medicoService.buscarPorId(id);
    }

    @PostMapping
    public MedicoResponseDTO salvar(@RequestBody MedicoRequestDTO dto) {
        return medicoService.salvar(dto);
    }

    @PutMapping("/{id}")
    public MedicoResponseDTO atualizar(@PathVariable Long id, @RequestBody MedicoRequestDTO dto) {
        return medicoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.medicoService.remover(id);
    }
}
