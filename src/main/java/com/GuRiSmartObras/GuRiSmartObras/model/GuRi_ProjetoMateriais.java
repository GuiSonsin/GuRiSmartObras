package com.GuRiSmartObras.GuRiSmartObras.model;

import jakarta.persistence.*;

@Entity(name = "projetomateriais")
public class GuRi_ProjetoMateriais {

    private int projetoId;
    @ManyToMany
    @JoinColumn(name = "materialId", nullable = false, insertable = false, updatable = false)
    private GuRi_Materiais material;
    private int quantidade;

    public int getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
    }

    public GuRi_Materiais getMaterial() {
        return material;
    }

    public void setMaterialId(GuRi_Materiais material) {
        this.material = material;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}