package com.sistema.hospitalapi.service;

import com.sistema.hospitalapi.dto.PacienteRequestDTO;
import com.sistema.hospitalapi.dto.PacienteResponseDTO;
import com.sistema.hospitalapi.model.Paciente;
import com.sistema.hospitalapi.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    private Paciente toEntity(PacienteRequestDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setTelefone(dto.getTelefone());

        return paciente;
    }

    private PacienteResponseDTO toDTO(Paciente paciente) {
        return PacienteResponseDTO.builder()
                .id(paciente.getId())
                .nome(paciente.getNome())
                .cpf(paciente.getCpf())
                .telefone(paciente.getTelefone())
                .build();
    }

    public List<PacienteResponseDTO> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public PacienteResponseDTO buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        return toDTO(paciente);
    }

    public PacienteResponseDTO salvar(PacienteRequestDTO dto) {
        Paciente paciente = toEntity(dto);
        Paciente salvo = pacienteRepository.save(paciente);
        return toDTO(salvo);
    }

    public PacienteResponseDTO atualizar(Long id, PacienteRequestDTO dto) {
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));

        pacienteExistente.setNome(dto.getNome());
        pacienteExistente.setCpf(dto.getCpf());
        pacienteExistente.setTelefone(dto.getTelefone());
        Paciente pacienteAtualizado = pacienteRepository.save(pacienteExistente);
        return toDTO(pacienteAtualizado);
    }

    public void remover(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));

        pacienteRepository.delete(paciente);
    }
}
