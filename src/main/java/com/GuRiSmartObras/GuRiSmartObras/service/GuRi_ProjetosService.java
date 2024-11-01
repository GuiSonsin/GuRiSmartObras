package com.GuRiSmartObras.GuRiSmartObras.service;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Projetos;
import com.GuRiSmartObras.GuRiSmartObras.repository.GuRi_ProjetosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuRi_ProjetosService {
    GuRi_ProjetosRepository projetosRepository;

    @Autowired
    public GuRi_ProjetosService(GuRi_ProjetosRepository projetosRepository) {
        this.projetosRepository = projetosRepository;
    }

    public List<GuRi_Projetos> listarProjetos(){
        return projetosRepository.findAll();
    }

    public GuRi_Projetos cadastrarProjeto(GuRi_Projetos projeto){
        return projetosRepository.save(projeto);
    }

    public GuRi_Projetos buscarProjetoPorID(int id){
        return projetosRepository.findById(id).orElse(null);
    }

    public GuRi_Projetos atualizarDadosProjeto(GuRi_Projetos projeto){
        return projetosRepository.save(projeto);
    }

    public void removeProjeto(int projetoId){
        projetosRepository.deleteById(projetoId);
    }
}
