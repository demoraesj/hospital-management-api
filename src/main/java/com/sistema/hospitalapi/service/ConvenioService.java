package com.sistema.hospitalapi.service;

import com.sistema.hospitalapi.model.Convenio;
import com.sistema.hospitalapi.repository.ConvenioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenioService {
    private ConvenioRepository convenioRepository;

    public ConvenioService(ConvenioRepository convenioRepository) {
        this.convenioRepository = convenioRepository;
    }

    public List<Convenio> listarTodos() {
        return convenioRepository.findAll();
    }

    public Convenio buscarPorId(Long id) {
        return convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convenio não encontrado!"));
    }

    public Convenio salvar(Convenio convenio) {
        return convenioRepository.save(convenio);
    }

    public Convenio atualizar(Long id, Convenio convenio) {

        Convenio convenioExistente = convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));

        convenioExistente.setNome(convenio.getNome());
        convenioExistente.setCnpj(convenio.getCnpj());

        return convenioRepository.save(convenioExistente);
    }

    public void remover(Long id) {
        Convenio convenio = convenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado!"));

        convenioRepository.delete(convenio);
    }
}
