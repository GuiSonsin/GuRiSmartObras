package com.GuRiSmartObras.GuRiSmartObras.repository;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_ProjetoMateriais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuRi_ProjetoMateriaisRepository extends JpaRepository<GuRi_ProjetoMateriais, Integer> {
    Optional<GuRi_ProjetoMateriais> findById_ProjetoIdAndId_MaterialId(int projetoId, int materialId);

    void delete(GuRi_ProjetoMateriais projetoMaterial);
}
