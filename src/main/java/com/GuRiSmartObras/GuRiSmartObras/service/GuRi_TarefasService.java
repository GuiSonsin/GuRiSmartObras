package com.GuRiSmartObras.GuRiSmartObras.service;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Clientes;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Funcionarios;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Projetos;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Tarefas;
import com.GuRiSmartObras.GuRiSmartObras.repository.GuRi_FuncionariosRepository;
import com.GuRiSmartObras.GuRiSmartObras.repository.GuRi_ProjetosRepository;
import com.GuRiSmartObras.GuRiSmartObras.repository.GuRi_TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuRi_TarefasService {
    GuRi_TarefasRepository tarefasRepository;

    @Autowired
    private GuRi_FuncionariosRepository funcionarioRepository;

    @Autowired
    private GuRi_ProjetosRepository projetoRepository;

    @Autowired
    public GuRi_TarefasService(GuRi_TarefasRepository tarefasRepository) {
        this.tarefasRepository = tarefasRepository;
    }

    public List<GuRi_Tarefas> listarTarefas(){
        return tarefasRepository.findAll();
    }

    public GuRi_Tarefas cadastrarTarefa(GuRi_Tarefas tarefa){
        if (tarefa.getFuncionario() == null) {
            throw new IllegalArgumentException("O ID do funcionario nao foi fornecido.");
        }
        if (tarefa.getProjeto() == null) {
            throw new IllegalArgumentException("O ID do projeto nao foi fornecido.");
        }

        GuRi_Funcionarios funcionario = funcionarioRepository.findById(tarefa.getFuncionario().getFuncionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionario nao encontrado"));

        GuRi_Projetos projeto = projetoRepository.findById(tarefa.getProjeto().getProjetoId())
                .orElseThrow(() -> new RuntimeException("Projeto nao encontrado"));

        tarefa.setFuncionario(funcionario);
        tarefa.setProjeto(projeto);
        return tarefasRepository.save(tarefa);
    }

    public GuRi_Tarefas buscarTarefaPorID(int id){
        return tarefasRepository.findById(id).orElse(null);
    }

    public GuRi_Tarefas atualizarDadosTarefa(GuRi_Tarefas tarefa){
        if (tarefa.getFuncionario() == null) {
            throw new IllegalArgumentException("O ID do funcionario nao foi fornecido.");
        }
        if (tarefa.getProjeto() == null) {
            throw new IllegalArgumentException("O ID do projeto nao foi fornecido.");
        }

        GuRi_Funcionarios funcionario = funcionarioRepository.findById(tarefa.getFuncionario().getFuncionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionario nao encontrado"));

        GuRi_Projetos projeto = projetoRepository.findById(tarefa.getProjeto().getProjetoId())
                .orElseThrow(() -> new RuntimeException("Projeto nao encontrado"));

        tarefa.setFuncionario(funcionario);
        tarefa.setProjeto(projeto);
        return tarefasRepository.save(tarefa);
    }

    public void removeTarefa(int tarefaId){
        tarefasRepository.deleteById(tarefaId);
    }
}
