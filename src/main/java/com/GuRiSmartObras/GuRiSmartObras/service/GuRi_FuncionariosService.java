package com.GuRiSmartObras.GuRiSmartObras.service;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Funcionarios;
import com.GuRiSmartObras.GuRiSmartObras.repository.GuRi_FuncionariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuRi_FuncionariosService {
    GuRi_FuncionariosRepository funcionariosRepository;

    @Autowired
    public GuRi_FuncionariosService(GuRi_FuncionariosRepository funcionariosRepository) {
        this.funcionariosRepository = funcionariosRepository;
    }

    public List<GuRi_Funcionarios> listarFuncionarios(){
        return funcionariosRepository.findAll();
    }

    public GuRi_Funcionarios cadastrarFuncionario(GuRi_Funcionarios funcionario){
        return funcionariosRepository.save(funcionario);
    }

    public GuRi_Funcionarios buscarFuncionarioPorID(int id){
        return funcionariosRepository.findById(id).orElse(null);
    }

    public GuRi_Funcionarios atualizarDadosFuncionario(GuRi_Funcionarios funcionario){
        return funcionariosRepository.save(funcionario);
    }

    public void removeFuncionario(int funcionarioId){
        funcionariosRepository.deleteById(funcionarioId);
    }
}
