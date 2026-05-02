package com.sistema.hospitalapi.service;

import com.sistema.hospitalapi.dto.ProntuarioRequestDTO;
import com.sistema.hospitalapi.dto.ProntuarioResponseDTO;
import com.sistema.hospitalapi.model.Paciente;
import com.sistema.hospitalapi.model.Prontuario;
import com.sistema.hospitalapi.repository.PacienteRepository;
import com.sistema.hospitalapi.repository.ProntuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProntuarioService {
    private final ProntuarioRepository prontuarioRepository;
    private final PacienteRepository pacienteRepository;

    public ProntuarioService(ProntuarioRepository prontuarioRepository, PacienteRepository pacienteRepository) {
        this.prontuarioRepository = prontuarioRepository;
        this.pacienteRepository = pacienteRepository;
    }

    private Prontuario toEntity(ProntuarioRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Prontuario prontuario = new Prontuario();
        prontuario.setTipoSanguineo(dto.getTipoSanguineo());
        prontuario.setAlergia(dto.getAlergia());
        prontuario.setObservacoes(dto.getObservacoes());

        prontuario.setPaciente(paciente);

        return prontuario;
    }

    private ProntuarioResponseDTO toDTO(Prontuario prontuario) {
        return ProntuarioResponseDTO.builder()
                .id(prontuario.getId())
                .tipoSanguineo(prontuario.getTipoSanguineo())
                .alergia(prontuario.getAlergia())
                .observacoes(prontuario.getObservacoes())
                .pacienteNome(prontuario.getPaciente().getNome())
                .build();
    }

    public List<ProntuarioResponseDTO> listarTodos() {
        return prontuarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ProntuarioResponseDTO buscarPorId(Long id) {
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuario não encontrado"));

        return toDTO(prontuario);
    }

    public ProntuarioResponseDTO salvar(ProntuarioRequestDTO dto) {
        Prontuario prontuario = toEntity(dto);
        Prontuario salvo = prontuarioRepository.save(prontuario);
        return toDTO(salvo);
    }

    public ProntuarioResponseDTO atualizar(Long id, ProntuarioRequestDTO dto) {

        Prontuario prontuarioExistente = prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuario não encontrado"));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        prontuarioExistente.setTipoSanguineo(dto.getTipoSanguineo());
        prontuarioExistente.setAlergia(dto.getAlergia());
        prontuarioExistente.setObservacoes(dto.getObservacoes());
        prontuarioExistente.setPaciente(paciente);

        Prontuario prontuarioAtualizado = prontuarioRepository.save(prontuarioExistente);

        return toDTO(prontuarioAtualizado);
    }

    public void remover(Long id) {
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuario não encontrado!"));

        prontuarioRepository.delete(prontuario);
    }
}
