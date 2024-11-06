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
    private LocalDate datainicio;
    private LocalDate datafim;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private GuRi_Clientes cliente;
    private String status;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private GuRi_Funcionarios funcionario;

    public GuRi_Projetos(String nome, String descricao, LocalDate datainicio, LocalDate datafim, GuRi_Clientes cliente, String status, GuRi_Funcionarios funcionario) {
        this.nome = nome;
        this.descricao = descricao;
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.cliente = cliente;
        this.status = status;
        this.funcionario = funcionario;
    }

    public GuRi_Projetos() {
    }

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
