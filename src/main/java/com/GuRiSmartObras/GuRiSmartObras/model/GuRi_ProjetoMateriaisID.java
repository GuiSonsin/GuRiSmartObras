package com.GuRiSmartObras.GuRiSmartObras.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class GuRi_ProjetoMateriaisID {
    private int projetoId;
    private int materialId;

    public int getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }
}
