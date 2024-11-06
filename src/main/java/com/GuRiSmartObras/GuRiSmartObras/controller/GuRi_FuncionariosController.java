package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Funcionarios;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_FuncionariosService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<GuRi_Funcionarios> criarFuncionario(@RequestBody GuRi_Funcionarios funcionario){
        GuRi_Funcionarios funcionarioCriado = funcionariosService.cadastrarFuncionario(funcionario);
        return ResponseEntity.ok(funcionarioCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuRi_Funcionarios> atualizarFuncionario(@PathVariable int id,
                                                          @RequestBody GuRi_Funcionarios funcionarioAtualizado) throws Exception {
        GuRi_Funcionarios funcionarioExiste = funcionariosService.buscarFuncionarioPorID(id);

        if(funcionarioExiste == null){
            return ResponseEntity.notFound().build();
        }

        funcionarioExiste.setNome(funcionarioAtualizado.getNome());
        funcionarioExiste.setCargo(funcionarioAtualizado.getCargo());
        if(funcionarioAtualizado.getSalario() < 1000){
            throw new Exception("O salario do funcionario nao pode ser menor que 1000");
        }
        funcionarioExiste.setSalario(funcionarioAtualizado.getSalario());

        GuRi_Funcionarios funcionarioAtt = funcionariosService.atualizarDadosFuncionario(funcionarioExiste);
        return ResponseEntity.ok(funcionarioAtt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GuRi_Funcionarios> removeFuncionario(@PathVariable int id){
        GuRi_Funcionarios funcionarioExiste = funcionariosService.buscarFuncionarioPorID(id);

        if(funcionarioExiste == null){
            return ResponseEntity.notFound().build();
        }

        funcionariosService.removeFuncionario(funcionarioExiste.getFuncionarioId());
        return ResponseEntity.ok(funcionarioExiste);
    }
}
