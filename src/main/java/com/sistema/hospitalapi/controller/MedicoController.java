package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.dto.MedicoRequestDTO;
import com.sistema.hospitalapi.dto.MedicoResponseDTO;
import com.sistema.hospitalapi.service.MedicoService;
import jakarta.validation.Valid;
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
    public MedicoResponseDTO salvar(@Valid @RequestBody MedicoRequestDTO dto) {
        return medicoService.salvar(dto);
    }

    @PutMapping("/{id}")
    public MedicoResponseDTO atualizar(@PathVariable Long id,@Valid @RequestBody MedicoRequestDTO dto) {
        return medicoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.medicoService.remover(id);
    }
}
