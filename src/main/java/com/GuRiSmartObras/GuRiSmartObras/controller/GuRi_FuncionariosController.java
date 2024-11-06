package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Funcionarios;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_FuncionariosService;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class GuRi_FuncionariosController {
    GuRi_FuncionariosService funcionariosService;

    @Autowired
    public GuRi_FuncionariosController(GuRi_FuncionariosService funcionariosService) {
        this.funcionariosService = funcionariosService;
    }

    @GetMapping
    public List<GuRi_Funcionarios> listarFuncionarios(){
        return funcionariosService.listarFuncionarios();
    }

    @PostMapping
    public ResponseEntity<String> criarFuncionario(@RequestBody GuRi_Funcionarios funcionario){
        GuRi_Funcionarios funcionarioCriado = funcionariosService.cadastrarFuncionario(funcionario);
        return ResponseEntity.ok("Funcionario cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarFuncionario(@PathVariable int id,
                                                          @RequestBody GuRi_Funcionarios funcionarioAtualizado) {
        GuRi_Funcionarios funcionarioExiste = funcionariosService.buscarFuncionarioPorID(id);

        if(funcionarioExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario nao encontrado com funcionarioId " + id);
        }

        funcionarioExiste.setNome(funcionarioAtualizado.getNome());
        funcionarioExiste.setCargo(funcionarioAtualizado.getCargo());
        if(funcionarioAtualizado.getSalario() < 1000){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O salario do funcionario nao pode ser menor que 1000");
        }
        funcionarioExiste.setSalario(funcionarioAtualizado.getSalario());

        funcionariosService.atualizarDadosFuncionario(funcionarioExiste);

        return ResponseEntity.ok("Funcionario atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFuncionario(@PathVariable int id){
        GuRi_Funcionarios funcionarioExiste = funcionariosService.buscarFuncionarioPorID(id);

        if(funcionarioExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario nao encontrado com funcionarioId " + id);
        }

        funcionariosService.removeFuncionario(funcionarioExiste.getFuncionarioId());
        return ResponseEntity.ok("Funcionario removido com sucesso!");
    }
}
