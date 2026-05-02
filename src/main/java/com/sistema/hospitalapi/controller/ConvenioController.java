package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.dto.ConvenioRequestDTO;
import com.sistema.hospitalapi.dto.ConvenioResponseDTO;
import com.sistema.hospitalapi.service.ConvenioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convenio")
public class ConvenioController {
    private final ConvenioService convenioService;

    public ConvenioController(ConvenioService convenioService) {
        this.convenioService = convenioService;
    }

    @GetMapping
    public List<ConvenioResponseDTO> listarTodas() {
        return convenioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ConvenioResponseDTO buscarPorId(@PathVariable Long id) {
        return convenioService.buscarPorId(id);
    }

    @PostMapping
    public ConvenioResponseDTO salvar(@RequestBody ConvenioRequestDTO dto) {
        return convenioService.salvar(dto);
    }

    @PutMapping("/{id}")
    public ConvenioResponseDTO atualizar(@PathVariable Long id, @RequestBody ConvenioRequestDTO dto) {
        return convenioService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        this.convenioService.remover(id);
    }

}
