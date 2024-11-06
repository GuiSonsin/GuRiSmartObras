package com.GuRiSmartObras.GuRiSmartObras.service;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Clientes;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Funcionarios;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Projetos;
import com.GuRiSmartObras.GuRiSmartObras.repository.GuRi_ClientesRepository;
import com.GuRiSmartObras.GuRiSmartObras.repository.GuRi_FuncionariosRepository;
import com.GuRiSmartObras.GuRiSmartObras.repository.GuRi_ProjetosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuRi_ProjetosService {
    GuRi_ProjetosRepository projetosRepository;

    @Autowired
    private GuRi_FuncionariosRepository funcionarioRepository;

    @Autowired
    private GuRi_ClientesRepository clienteRepository;

    @Autowired
    public GuRi_ProjetosService(GuRi_ProjetosRepository projetosRepository) {
        this.projetosRepository = projetosRepository;
    }

    public List<GuRi_Projetos> listarProjetos(){
        return projetosRepository.findAll();
    }

    public GuRi_Projetos cadastrarProjeto(GuRi_Projetos projeto){
        if (projeto.getFuncionario() == null) {
            throw new IllegalArgumentException("O ID do funcionário nao foi fornecido.");
        }
        if (projeto.getCliente() == null) {
            throw new IllegalArgumentException("O ID do cliente nao foi fornecido.");
        }

        GuRi_Funcionarios funcionario = funcionarioRepository.findById(projeto.getFuncionario().getFuncionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionario nao encontrado"));

        GuRi_Clientes cliente = clienteRepository.findById(projeto.getCliente().getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));

        projeto.setFuncionario(funcionario);
        projeto.setCliente(cliente);

        return projetosRepository.save(projeto);
    }

    public GuRi_Projetos buscarProjetoPorID(int id){
        return projetosRepository.findById(id).orElse(null);
    }

    public GuRi_Projetos atualizarDadosProjeto(GuRi_Projetos projeto){
        if (projeto.getFuncionario() == null) {
            throw new IllegalArgumentException("O ID do funcionário nao foi fornecido.");
        }
        if (projeto.getCliente() == null) {
            throw new IllegalArgumentException("O ID do cliente nao foi fornecido.");
        }

        GuRi_Funcionarios funcionario = funcionarioRepository.findById(projeto.getFuncionario().getFuncionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionario nao encontrado"));

        GuRi_Clientes cliente = clienteRepository.findById(projeto.getCliente().getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));

        projeto.setFuncionario(funcionario);
        projeto.setCliente(cliente);
        return projetosRepository.save(projeto);
    }

    public void removeProjeto(int projetoId){
        projetosRepository.deleteById(projetoId);
    }
}
