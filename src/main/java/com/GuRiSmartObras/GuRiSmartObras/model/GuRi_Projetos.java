package com.GuRiSmartObras.GuRiSmartObras.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity(name = "projetos")
public class GuRi_Projetos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int projetoId;
    private String nome;
    private String descricao;
    @CreationTimestamp
    private LocalDate dataInicio;
    private LocalDate dataFim;
    @OneToOne
    @JoinColumn(name = "clienteId", nullable = false, insertable = false, updatable = false)
    private GuRi_Clientes cliente;
    private String status;
    @OneToOne
    @JoinColumn(name = "funcionarioId", nullable = false, insertable = false, updatable = false)
    private GuRi_Funcionarios funcionario;

    public int getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
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

    public GuRi_Clientes getCliente() {
        return cliente;
    }

    public void setCliente(GuRi_Clientes cliente) {
        this.cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GuRi_Funcionarios getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(GuRi_Funcionarios funcionario) {
        this.funcionario = funcionario;
    }
}
