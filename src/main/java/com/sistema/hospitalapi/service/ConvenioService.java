package com.sistema.hospitalapi.service;

import com.sistema.hospitalapi.dto.ConvenioRequestDTO;
import com.sistema.hospitalapi.dto.ConvenioResponseDTO;
import com.sistema.hospitalapi.model.Convenio;
import com.sistema.hospitalapi.repository.ConvenioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenioService {
    private final ConvenioRepository convenioRepository;

    public ConvenioService(ConvenioRepository convenioRepository) {
        this.convenioRepository = convenioRepository;
    }

    private Convenio toEntity(ConvenioRequestDTO dto) {
        Convenio convenio = new Convenio();
        convenio.setNome(dto.getNome());
        convenio.setCnpj(dto.getCnpj());
        return convenio;
    }

    private ConvenioResponseDTO toDTO(Convenio convenio) {
        return ConvenioResponseDTO.builder()
                .id(convenio.getId())
                .nome(convenio.getNome())
                .cnpj(convenio.getCnpj())
                .build();

    }

    public List<ConvenioResponseDTO> listarTodos() {
        return convenioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ConvenioResponseDTO buscarPorId(Long id) {
        Convenio convenio = convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convênio não encontrado"));

        return toDTO(convenio);
    }

    public ConvenioResponseDTO salvar(ConvenioRequestDTO dto) {
        Convenio convenio = toEntity(dto);
        Convenio salvo = convenioRepository.save(convenio);
        return toDTO(salvo);
    }

    public ConvenioResponseDTO atualizar(Long id, ConvenioRequestDTO dto) {

        Convenio convenioExistente = convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convênio não encontrado"));

        convenioExistente.setNome(dto.getNome());
        convenioExistente.setCnpj(dto.getCnpj());

        Convenio convenioAtualizado = convenioRepository.save(convenioExistente);

        return toDTO(convenioAtualizado);
    }

    public void remover(Long id) {
        Convenio convenio = convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convênio não encontrado"));

        convenioRepository.delete(convenio);
    }
}
