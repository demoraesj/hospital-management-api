package com.sistema.hospitalapi.service;

import com.sistema.hospitalapi.dto.ReceitaRequestDTO;
import com.sistema.hospitalapi.dto.ReceitaResponseDTO;
import com.sistema.hospitalapi.model.Consulta;
import com.sistema.hospitalapi.model.Receita;
import com.sistema.hospitalapi.repository.ConsultaRepository;
import com.sistema.hospitalapi.repository.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {
    private final ReceitaRepository receitaRepository;
    private final ConsultaRepository consultaRepository;

    public ReceitaService(ReceitaRepository receitaRepository, ConsultaRepository consultaRepository) {
        this.receitaRepository = receitaRepository;
        this.consultaRepository = consultaRepository;
    }

    private Receita toEntity(ReceitaRequestDTO dto) {
        Consulta consulta = consultaRepository.findById(dto.getConsultaId())
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        Receita receita = new Receita();
        receita.setMedicamento(dto.getMedicamento());
        receita.setDosagem(dto.getDosagem());
        receita.setDuracaoDias(dto.getDuracaoDias());

        receita.setConsulta(consulta);

        return receita;
    }

    private ReceitaResponseDTO toDTO(Receita receita) {
        return ReceitaResponseDTO.builder()
                .id(receita.getId())
                .medicamento(receita.getMedicamento())
                .dosagem(receita.getDosagem())
                .duracaoDias(receita.getDuracaoDias())
                .consultaId(receita.getConsulta().getId())
                .build();
    }

    public List<ReceitaResponseDTO> listarTodos() {
        return receitaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ReceitaResponseDTO buscarPorId(Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        return toDTO(receita);
    }

    public ReceitaResponseDTO salvar(ReceitaRequestDTO dto) {
        Receita receita = toEntity(dto);
        Receita salva = receitaRepository.save(receita);
        return toDTO(salva);
    }

    public ReceitaResponseDTO atualizar(Long id, ReceitaRequestDTO dto) {
        Receita receitaExistente = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        Consulta consulta = consultaRepository.findById(dto.getConsultaId())
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        receitaExistente.setMedicamento(dto.getMedicamento());
        receitaExistente.setDosagem(dto.getDosagem());
        receitaExistente.setDuracaoDias(dto.getDuracaoDias());

        receitaExistente.setConsulta(consulta);

        Receita receitaAtualizada = receitaRepository.save(receitaExistente);

        return toDTO(receitaAtualizada);
    }

    public void remover(Long id) {

        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        receitaRepository.delete(receita);
    }

}
