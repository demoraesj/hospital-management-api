package com.sistema.hospitalapi.service;

import com.sistema.hospitalapi.model.Convenio;
import com.sistema.hospitalapi.model.Receita;
import com.sistema.hospitalapi.repository.ConvenioRepository;
import com.sistema.hospitalapi.repository.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {
    private ReceitaRepository receitaRepository;

    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public List<Receita> listarTodos() {
        return receitaRepository.findAll();
    }

    public Receita buscarPorId(Long id) {
        return receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada!"));
    }

    public Receita salvar(Receita receita) {
        return receitaRepository.save(receita);
    }

    public Receita atualizar(Long id, Receita receita) {

        Receita receitaExistente = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado!"));

        receitaExistente.setMedicamento(receita.getMedicamento());
        receitaExistente.setDosagem(receita.getDosagem());
        receitaExistente.setDuracaoDias(receita.getDuracaoDias());
        receitaExistente.setConsulta(receita.getConsulta());

        return receitaRepository.save(receitaExistente);
    }

    public void remover(Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado!"));

        receitaRepository.delete(receita);
    }
}
