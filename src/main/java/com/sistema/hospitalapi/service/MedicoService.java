package com.sistema.hospitalapi.service;

import com.sistema.hospitalapi.dto.MedicoRequestDTO;
import com.sistema.hospitalapi.dto.MedicoResponseDTO;
import com.sistema.hospitalapi.model.Medico;
import com.sistema.hospitalapi.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    private Medico toEntity(MedicoRequestDTO dto) {
        Medico medico = new Medico();
        medico.setNome(dto.getNome());
        medico.setEspecialidade(dto.getEspecialidade());
        medico.setCrm(dto.getCrm());

        return medico;
    }

    private MedicoResponseDTO toDTO(Medico medico) {
        return MedicoResponseDTO.builder()
                .id(medico.getId())
                .nome(medico.getNome())
                .especialidade(medico.getEspecialidade())
                .crm(medico.getCrm())
                .build();
    }

    public List<MedicoResponseDTO> listarTodos() {
        return medicoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public MedicoResponseDTO buscarPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medico não encontrado"));

        return toDTO(medico);
    }

    public MedicoResponseDTO salvar(MedicoRequestDTO dto) {
        Medico medico = toEntity(dto);
        Medico salvo = medicoRepository.save(medico);
        return toDTO(salvo);
    }

    public MedicoResponseDTO atualizar(Long id, MedicoRequestDTO dto) {
        Medico medicoExistente = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medico não encontrado"));

        medicoExistente.setNome(dto.getNome());
        medicoExistente.setEspecialidade(dto.getEspecialidade());
        medicoExistente.setCrm(dto.getCrm());

        Medico medicoAtualizado = medicoRepository.save(medicoExistente);
        return toDTO(medicoAtualizado);
    }

    public void remover(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        medicoRepository.delete(medico);
    }
}
