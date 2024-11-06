package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Clientes;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class GuRi_ClientesController {
    GuRi_ClientesService clientesService;

    @Autowired
    public GuRi_ClientesController(GuRi_ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public List<GuRi_Clientes> listarClientes(){
        return clientesService.listarClientes();
    }

    @PostMapping
    public ResponseEntity<String> criarCliente(@RequestBody GuRi_Clientes cliente){
        clientesService.cadastrarCliente(cliente);
        return ResponseEntity.ok("Cliente cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarCliente(@PathVariable int id,
            @RequestBody GuRi_Clientes clienteAtualizado){
        GuRi_Clientes clienteExiste = clientesService.buscarClientePorID(id);

        if(clienteExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente nao encontrado com clienteId " + id);
        }

        clienteExiste.setNome(clienteAtualizado.getNome());
        clienteExiste.setEndereco(clienteAtualizado.getEndereco());
        clienteExiste.setTelefone(clienteAtualizado.getTelefone());

        clientesService.atualizarDadosCliente(clienteExiste);
        return ResponseEntity.ok("Cliente atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCliente(@PathVariable int id){
        GuRi_Clientes clienteExiste = clientesService.buscarClientePorID(id);

        if(clienteExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente nao encontrado com clienteId " + id);
        }

        clientesService.removeCliente(clienteExiste.getClienteId());
        return ResponseEntity.ok("Cliente removido com sucesso!");
    }
}
