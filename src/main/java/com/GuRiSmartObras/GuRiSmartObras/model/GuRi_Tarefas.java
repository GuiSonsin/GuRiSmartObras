package com.GuRiSmartObras.GuRiSmartObras.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity(name = "tarefas")
public class GuRi_Tarefas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tarefaId;
    private String nome;
    private String descricao;
    private LocalDate datainicio;
    private LocalDate datafim;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private GuRi_Projetos projeto;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private GuRi_Funcionarios funcionario;

    public int getTarefaId() {
        return tarefaId;
    }

    public void setTarefaId(int tarefaId) {
        this.tarefaId = tarefaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return datainicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.datainicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return datafim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.datafim = dataFim;
    }

    public GuRi_Projetos getProjeto() {
        return projeto;
    }

    public void setProjeto(GuRi_Projetos projeto) {
        this.projeto = projeto;
    }

    public GuRi_Funcionarios getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(GuRi_Funcionarios funcionario) {
        this.funcionario = funcionario;
    }
}