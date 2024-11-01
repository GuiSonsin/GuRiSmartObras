package com.GuRiSmartObras.GuRiSmartObras.service;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Materiais;
import com.GuRiSmartObras.GuRiSmartObras.repository.GuRi_MateriaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuRi_MateriaisService {
    GuRi_MateriaisRepository materiaisRepository;

    @Autowired
    public GuRi_MateriaisService(GuRi_MateriaisRepository materiaisRepository) {
        this.materiaisRepository = materiaisRepository;
    }

    public List<GuRi_Materiais> listarMateriais(){
        return materiaisRepository.findAll();
    }

    public GuRi_Materiais cadastrarMaterial(GuRi_Materiais material){
        return materiaisRepository.save(material);
    }

    public GuRi_Materiais buscarMaterialPorID(int id){
        return materiaisRepository.findById(id).orElse(null);
    }

    public GuRi_Materiais atualizarDadosMaterial(GuRi_Materiais material){
        return materiaisRepository.save(material);
    }

    public void removeMaterial(int materialId){
        materiaisRepository.deleteById(materialId);
    }
}
