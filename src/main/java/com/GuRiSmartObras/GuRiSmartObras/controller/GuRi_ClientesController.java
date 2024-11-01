package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Clientes;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_ClientesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class GuRi_ClientesController {
    GuRi_ClientesService clientesService;

    public GuRi_ClientesController(GuRi_ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public List<GuRi_Clientes> listarClientes(){
        return clientesService.listarClientes();
    }

    @PostMapping
    public ResponseEntity<GuRi_Clientes> criarCurso(@RequestBody GuRi_Clientes cliente){
        GuRi_Clientes clienteCriado = clientesService.cadastrarCliente(cliente);
        return ResponseEntity.ok(clienteCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuRi_Clientes> atualizarCliente(@PathVariable int id,
            @RequestBody GuRi_Clientes clienteAtualizado){
        GuRi_Clientes clienteExiste = clientesService.buscarClientePorID(id);

        if(clienteExiste == null){
            return ResponseEntity.notFound().build();
        }

        clienteExiste.setNome(clienteAtualizado.getNome());
        clienteExiste.setEndereco(clienteAtualizado.getEndereco());
        clienteExiste.setTelefone(clienteAtualizado.getTelefone());

        GuRi_Clientes clienteAtt = clientesService.atualizarDadosCliente(clienteExiste);
        return ResponseEntity.ok(clienteAtt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GuRi_Clientes> removeCliente(@PathVariable int id){
        GuRi_Clientes clienteExiste = clientesService.buscarClientePorID(id);

        if(clienteExiste == null){
            return ResponseEntity.notFound().build();
        }

        clientesService.removeCliente(clienteExiste.getClienteId());
        return ResponseEntity.ok(clienteExiste);
    }
}
