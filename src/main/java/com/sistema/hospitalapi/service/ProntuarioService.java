package com.sistema.hospitalapi.service;

import com.sistema.hospitalapi.model.Consulta;
import com.sistema.hospitalapi.model.Prontuario;
import com.sistema.hospitalapi.repository.ProntuarioRepository;

import java.util.List;

public class ProntuarioService {
    private ProntuarioRepository prontuarioRepository;

    public ProntuarioService(ProntuarioRepository prontuarioRepository) {
        this.prontuarioRepository = prontuarioRepository;
    }

    public List<Prontuario> listarTodos() {
        return prontuarioRepository.findAll();
    }

    public Prontuario buscarPorId(Long id) {
        return prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prontuario não encontrado!"));
    }

    public Prontuario salvar(Prontuario prontuario) {
        return prontuarioRepository.save(prontuario);
    }

    public Prontuario atualizar(Long id, Prontuario prontuario) {

        Prontuario prontuarioExistente = prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado!"));

        prontuarioExistente.setTipoSanguineo(prontuario.getTipoSanguineo());
        prontuarioExistente.setAlergia(prontuario.getAlergia());
        prontuarioExistente.setObservacoes(prontuario.getObservacoes());
        prontuarioExistente.setPaciente(prontuario.getPaciente());

        return prontuarioRepository.save(prontuarioExistente);
    }

    public void remover(Long id) {
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado!"));

        prontuarioRepository.delete(prontuario);
    }
}
