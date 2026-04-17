package com.sistema.hospitalapi.service;

import com.sistema.hospitalapi.model.Paciente;
import com.sistema.hospitalapi.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
    }

    public Paciente salvar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente atualizar(Long id, Paciente paciente) {
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado!"));

        pacienteExistente.setNome(paciente.getNome());
        pacienteExistente.setCpf(paciente.getCpf());
        pacienteExistente.setTelefone(paciente.getTelefone());
        pacienteExistente.setProntuario(paciente.getProntuario());

        return pacienteRepository.save(pacienteExistente);
    }

    public void remover(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado!"));

        pacienteRepository.delete(paciente);
    }
}
