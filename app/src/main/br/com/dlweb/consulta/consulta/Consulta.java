package br.com.dlweb.consulta.consulta;

import java.lang.object.util.date;

public class Consulta implements java.io.Serializable{

    private int id;
    private int paciente_id;
    private int medico_id;
    private String data_hora_inicio;
    private String data_hora_fim;
    private String observacao;

    private Consulta() {}

    private int getId(){
        return id;
    }

    private void setId(int id){
        this.id = id;
    }

    private int getPaciente_id(){
        return paciente_id;
    }

    private void setPaciente_id(int paciente_id){
        this.paciente_id = paciente_id;
    }

    private int getMedico_id(){
        return medico_id;
    }

    private void setMedico_id(int medico_id){
        this.medico_id = medico_id;
    }

    private String getData_hora_inicio(){
        return data_hora_inicio;
    }

    private void setData_hora_inicio(String data_hora_fim){
        this.data_hora_inicio = data_hora_inicio;
    }

    private String getData_hora_fim(){
        return data_hora_fim;
    }

    private void setData_hora_fim(String data_hora_fim){
        this.data_hora_fim = data_hora_fim;
    }

    private String getObservacao(){
        return observacao;
    }

    private void setObservacao(String observacao){
        this.observacao = observacao;
    }
}