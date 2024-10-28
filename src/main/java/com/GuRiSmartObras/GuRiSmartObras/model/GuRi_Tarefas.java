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
    @CreationTimestamp
    private LocalDate dataInicio;
    private LocalDate dataFim;
    @ManyToOne
    @JoinColumn(name = "projetoId", nullable = false, insertable = false, updatable = false)
    private GuRi_Projetos projeto;
    @ManyToOne
    @JoinColumn(name = "funcionarioId", nullable = false, insertable = false, updatable = false)
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
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
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