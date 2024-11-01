package com.GuRiSmartObras.GuRiSmartObras.service;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Materiais;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_ProjetoMateriais;
import com.GuRiSmartObras.GuRiSmartObras.repository.GuRi_ProjetoMateriaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuRi_ProjetoMateriaisService {
    GuRi_ProjetoMateriaisRepository projetoMateriaisRepository;

    @Autowired
    public GuRi_ProjetoMateriaisService(GuRi_ProjetoMateriaisRepository projetoMateriaisRepository) {
        this.projetoMateriaisRepository = projetoMateriaisRepository;
    }

    public List<GuRi_ProjetoMateriais> listarProjetoMateriais(){
        return projetoMateriaisRepository.findAll();
    }

    public GuRi_ProjetoMateriais cadastrarProjetoMaterial(GuRi_ProjetoMateriais projetoMaterial){
        return projetoMateriaisRepository.save(projetoMaterial);
    }

    public GuRi_ProjetoMateriais buscarProjetoMaterialPorID(int id){
        return projetoMateriaisRepository.findById(id).orElse(null);
    }

    public GuRi_ProjetoMateriais atualizarDadosProjetoMaterial(GuRi_ProjetoMateriais projetoMaterial){
        return projetoMateriaisRepository.save(projetoMaterial);
    }

    public void removeProjetoMaterial(int projetoMaterialId){
        projetoMateriaisRepository.deleteById(projetoMaterialId);
    }
}
