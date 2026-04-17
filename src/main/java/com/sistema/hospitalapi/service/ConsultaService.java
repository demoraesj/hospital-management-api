package com.sistema.hospitalapi.service;

import com.sistema.hospitalapi.model.Consulta;
import com.sistema.hospitalapi.model.Prontuario;
import com.sistema.hospitalapi.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    private ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada!"));
    }

    public Consulta salvar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public Consulta atualizar(Long id, Consulta consulta) {

        Consulta consultaExistente = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado!"));

        consultaExistente.setDataHora(consulta.getDataHora());
        consultaExistente.setMotivo(consulta.getMotivo());
        consultaExistente.setValor(consulta.getValor());
        consultaExistente.setPaciente(consulta.getPaciente());
        consultaExistente.setMedico(consulta.getMedico());
        consultaExistente.setConvenio(consulta.getConvenio());
        consultaExistente.setReceita(consulta.getReceita());

        return consultaRepository.save(consultaExistente);
    }

    public void remover(Long id) {

        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado!"));

        consultaRepository.delete(consulta);
    }
}
