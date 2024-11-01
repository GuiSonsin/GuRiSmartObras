package com.GuRiSmartObras.GuRiSmartObras.service;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Clientes;
import com.GuRiSmartObras.GuRiSmartObras.repository.GuRi_ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuRi_ClientesService {
    GuRi_ClientesRepository clientesRepository;

    @Autowired
    public GuRi_ClientesService(GuRi_ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public List<GuRi_Clientes> listarClientes(){
        return clientesRepository.findAll();
    }

    public GuRi_Clientes cadastrarCliente(GuRi_Clientes cliente){
        return clientesRepository.save(cliente);
    }

    public GuRi_Clientes buscarClientePorID(int id){
        return clientesRepository.findById(id).orElse(null);
    }

    public GuRi_Clientes atualizarDadosCliente(GuRi_Clientes cliente){
        return clientesRepository.save(cliente);
    }

    public void removeCliente(int clienteId){
        clientesRepository.deleteById(clienteId);
    }
}
