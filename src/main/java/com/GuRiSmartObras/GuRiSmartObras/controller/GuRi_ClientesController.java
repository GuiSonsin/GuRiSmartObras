package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.dto.GuRi_ResponseMessage;
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
    public ResponseEntity<GuRi_ResponseMessage> criarCliente(@RequestBody GuRi_Clientes cliente){
        if (cliente.getNome() == null || cliente.getEndereco() == null || cliente.getTelefone() == null) {
            GuRi_ResponseMessage responseMessage = new GuRi_ResponseMessage("Informe todos os dados do cliente");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }

        GuRi_ResponseMessage responseMessage = new GuRi_ResponseMessage("Cliente cadastrado com sucesso!");
        clientesService.cadastrarCliente(cliente);
        return ResponseEntity.ok(responseMessage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuRi_ResponseMessage> atualizarCliente(@PathVariable int id,
            @RequestBody GuRi_Clientes clienteAtualizado){
        GuRi_Clientes clienteExiste = clientesService.buscarClientePorID(id);

        if(clienteExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Cliente nao encontrado com clienteId " + id));
        }

        clienteExiste.setNome(clienteAtualizado.getNome() == null ? clienteExiste.getNome() : clienteAtualizado.getNome());
        clienteExiste.setEndereco(clienteAtualizado.getEndereco() == null ? clienteExiste.getEndereco() : clienteAtualizado.getEndereco());
        clienteExiste.setTelefone(clienteAtualizado.getTelefone() == null ? clienteExiste.getTelefone() : clienteAtualizado.getTelefone());

        clientesService.atualizarDadosCliente(clienteExiste);
        return ResponseEntity.ok(new GuRi_ResponseMessage("Cliente atualizado com sucesso!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GuRi_ResponseMessage> removeCliente(@PathVariable int id){
        GuRi_Clientes clienteExiste = clientesService.buscarClientePorID(id);

        if(clienteExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Cliente nao encontrado com clienteId " + id));
        }

        clientesService.removeCliente(clienteExiste.getClienteId());
        return ResponseEntity.ok(new GuRi_ResponseMessage("Cliente removido com sucesso!"));
    }
}
