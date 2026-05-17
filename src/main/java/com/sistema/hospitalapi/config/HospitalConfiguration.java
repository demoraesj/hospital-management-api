package com.sistema.hospitalapi.config;

import com.sistema.hospitalapi.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Configuration
public class HospitalConfiguration {

    @Bean(name = "prontuario")
    public Prontuario prontuarioPadrao() {
        Prontuario prontuario = new Prontuario();

        prontuario.setId(1L);
        prontuario.setTipoSanguineo("tipo padrão A+");
        prontuario.setAlergia("Alergia padrão");
        prontuario.setObservacoes("Observações padrão");

        return prontuario;
    }

    @Bean(name = "medico")
    public Medico medicoPadrao() {
        Medico medico = new Medico();

        medico.setId(1L);
        medico.setNome("Medico padrão");
        medico.setEspecialidade("Especialidade padrão");
        medico.setCrm("1111-1");

        medico.setConsultas(new ArrayList<>());

        return medico;
    }

    @Bean(name = "convenio")
    public Convenio convenioPadrao() {
        Convenio convenio = new Convenio();

        convenio.setId(1L);
        convenio.setNome("Convênio padrão");
        convenio.setCnpj("000000-0001");

        convenio.setConsultas(new ArrayList<>());

        return convenio;
    }

    @Bean(name = "receita")
    public Receita receitaPadrao() {
        Receita receita = new Receita();

        receita.setId(1L);
        receita.setMedicamento("Medicamento padrão");
        receita.setDosagem("Dosagem padrão");
        receita.setDuracaoDias(1);

        return receita;
    }

    @Bean(name = "paciente")
    public Paciente pacientePadrao(Prontuario prontuarioPadrao) {
        Paciente paciente = new Paciente();

        paciente.setId(1L);
        paciente.setNome("Nome padrão");
        paciente.setCpf("111111-11");
        paciente.setTelefone("15-111111");

        paciente.setProntuario(prontuarioPadrao);
        paciente.setConsultas(new ArrayList<>());

        return paciente;
    }

    @Bean(name = "consulta")
    public Consulta consultaPadrao(Paciente pacientePadrao,
                                   Medico medicoPadrao,
                                   Convenio convenioPadrao,
                                   Receita receitaPadrao) {
        Consulta consulta = new Consulta();

        consulta.setId(1L);
        consulta.setMotivo("Motivo padrão");
        consulta.setValor(1d);

        consulta.setPaciente(pacientePadrao);
        consulta.setMedico(medicoPadrao);
        consulta.setConvenio(convenioPadrao);
        consulta.setReceita(receitaPadrao);

        pacientePadrao.getConsultas().add(consulta);
        medicoPadrao.getConsultas().add(consulta);
        convenioPadrao.getConsultas().add(consulta);
        receitaPadrao.setConsulta(consulta);
        consulta.setDataHora(LocalDateTime.now());

        return consulta;
    }
}
