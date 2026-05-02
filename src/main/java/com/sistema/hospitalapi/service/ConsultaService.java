package com.sistema.hospitalapi.service;

import com.sistema.hospitalapi.dto.ConsultaRequestDTO;
import com.sistema.hospitalapi.dto.ConsultaResponseDTO;
import com.sistema.hospitalapi.model.Consulta;
import com.sistema.hospitalapi.model.Medico;
import com.sistema.hospitalapi.model.Paciente;
import com.sistema.hospitalapi.repository.ConsultaRepository;
import com.sistema.hospitalapi.repository.MedicoRepository;
import com.sistema.hospitalapi.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    private Consulta toEntity(ConsultaRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Medico não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setDataHora(dto.getDataHora());
        consulta.setMotivo(dto.getMotivo());
        consulta.setValor(dto.getValor());

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);

        return consulta;
    }

    private ConsultaResponseDTO toDTO(Consulta consulta) {
        return ConsultaResponseDTO.builder()
                .id(consulta.getId())
                .dataHora(consulta.getDataHora())
                .pacienteNome(consulta.getPaciente().getNome())
                .medicoNome(consulta.getMedico().getNome())
                .motivo(consulta.getMotivo())
                .build();
    }

    public List<ConsultaResponseDTO> listarTodas() {
        return consultaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ConsultaResponseDTO buscarPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        return toDTO(consulta);
    }

    public ConsultaResponseDTO salvar(ConsultaRequestDTO dto) {
        Consulta consulta = toEntity(dto);
        Consulta salva = consultaRepository.save(consulta);
        return toDTO(salva);
    }

    public ConsultaResponseDTO atualizar(Long id, ConsultaRequestDTO dto) {

        Consulta consultaExistente = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Medico não encontrado"));

        consultaExistente.setDataHora(dto.getDataHora());
        consultaExistente.setMotivo(dto.getMotivo());
        consultaExistente.setValor(dto.getValor());

        consultaExistente.setPaciente(paciente);
        consultaExistente.setMedico(medico);

        Consulta consultaAtualizada = consultaRepository.save(consultaExistente);

        return toDTO(consultaAtualizada);
    }

    public void remover(Long id) {

        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrado"));

        consultaRepository.delete(consulta);
    }
}
