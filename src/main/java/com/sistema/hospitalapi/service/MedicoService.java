package com.sistema.hospitalapi.service;

import com.sistema.hospitalapi.model.Medico;
import com.sistema.hospitalapi.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {
    private MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado!"));
    }

    public Medico salvar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico atualizar(Long id, Medico medico) {
        Medico medicoExistente = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado!"));

        medicoExistente.setNome(medico.getNome());
        medicoExistente.setEspecialidade(medico.getEspecialidade());
        medicoExistente.setCrm(medico.getCrm());

        return medicoRepository.save(medicoExistente);
    }

    public void remover(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado!"));

        medicoRepository.delete(medico);
    }
}
