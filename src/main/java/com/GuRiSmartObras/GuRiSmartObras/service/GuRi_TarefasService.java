package com.GuRiSmartObras.GuRiSmartObras.service;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Tarefas;
import com.GuRiSmartObras.GuRiSmartObras.repository.GuRi_TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuRi_TarefasService {
    GuRi_TarefasRepository tarefasRepository;

    @Autowired
    public GuRi_TarefasService(GuRi_TarefasRepository tarefasRepository) {
        this.tarefasRepository = tarefasRepository;
    }

    public List<GuRi_Tarefas> listarTarefas(){
        return tarefasRepository.findAll();
    }

    public GuRi_Tarefas cadastrarTarefa(GuRi_Tarefas tarefa){
        return tarefasRepository.save(tarefa);
    }

    public GuRi_Tarefas buscarTarefaPorID(int id){
        return tarefasRepository.findById(id).orElse(null);
    }

    public GuRi_Tarefas atualizarDadosTarefa(GuRi_Tarefas tarefa){
        return tarefasRepository.save(tarefa);
    }

    public void removeTarefa(int tarefaId){
        tarefasRepository.deleteById(tarefaId);
    }
}
