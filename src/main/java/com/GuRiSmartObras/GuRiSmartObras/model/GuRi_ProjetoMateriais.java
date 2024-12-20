package com.GuRiSmartObras.GuRiSmartObras.model;

import jakarta.persistence.*;

@Entity(name = "projetomateriais")
public class GuRi_ProjetoMateriais {
    @EmbeddedId
    private GuRi_ProjetoMateriaisID id;

    @ManyToOne
    @MapsId("materialId")
    @JoinColumn(name = "material_id", nullable = false)
    private GuRi_Materiais material;
    private int quantidade;

    @ManyToOne
    @MapsId("projetoId")
    @JoinColumn(name = "projeto_id", nullable = false)
    private GuRi_Projetos projeto;

    public GuRi_ProjetoMateriais() {
    }

    public GuRi_ProjetoMateriais(GuRi_ProjetoMateriaisID id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public GuRi_ProjetoMateriaisID getId() {
        return id;
    }

    public void setId(GuRi_ProjetoMateriaisID id) {
        this.id = id;
    }

    public void setMaterial(GuRi_Materiais material) {
        this.material = material;
    }

    public GuRi_Projetos getProjeto() {
        return projeto;
    }

    public void setProjeto(GuRi_Projetos projeto) {
        this.projeto = projeto;
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
