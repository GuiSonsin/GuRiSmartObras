package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.dto.GuRi_ProjetoRequest;
import com.GuRiSmartObras.GuRiSmartObras.dto.GuRi_ResponseMessage;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Clientes;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Funcionarios;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Materiais;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Projetos;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_ClientesService;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_FuncionariosService;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_ProjetosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/projetos")
public class GuRi_ProjetosController {
    GuRi_ProjetosService projetosService;

    @Autowired
    GuRi_ClientesService clientesService;

    @Autowired
    GuRi_FuncionariosService funcionariosService;

    @Autowired
    public GuRi_ProjetosController(GuRi_ProjetosService projetosService) {
        this.projetosService = projetosService;
    }

    @GetMapping
    public List<GuRi_Projetos> listarProjetos(){
        return projetosService.listarProjetos();
    }

    @PostMapping
    public ResponseEntity<GuRi_ResponseMessage> cadastrarProjetos(@RequestBody GuRi_ProjetoRequest projeto){
        GuRi_Clientes cliente = clientesService.buscarClientePorID(projeto.getClienteId());

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Cliente nao encontrado com clienteId " + projeto.getClienteId()));
        }

        GuRi_Funcionarios funcionario = funcionariosService.buscarFuncionarioPorID(projeto.getFuncionarioId());

        if (funcionario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Funcionário nao encontrado com funcionarioId " + projeto.getFuncionarioId()));
        }

        if (projeto.getNome() == null || projeto.getDescricao() == null || projeto.getStatus() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GuRi_ResponseMessage("Informe todos os dados obrigatório para criar o projeto (Nome, Descrição, Status)"));
        }

        GuRi_Projetos projetoCriado = new GuRi_Projetos(
                projeto.getNome(),
                projeto.getDescricao(),
                projeto.getDatainicio(),
                projeto.getDatafim(),
                cliente,
                projeto.getStatus(),
                funcionario
        );

        projetosService.cadastrarProjeto(projetoCriado);
        return ResponseEntity.ok(new GuRi_ResponseMessage("Projeto cadastrado com sucesso!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarProjeto(@PathVariable int id, @RequestBody GuRi_Projetos projetoAtualizado){
        GuRi_Projetos projetoExiste = projetosService.buscarProjetoPorID(id);

        if (projetoExiste == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto nao encontrado com projetoId " + id);
        }

        projetoExiste.setNome(projetoAtualizado.getNome());
        projetoExiste.setCliente(projetoAtualizado.getCliente());
        projetoExiste.setDescricao(projetoAtualizado.getDescricao());
        projetoExiste.setFuncionario(projetoAtualizado.getFuncionario());
        projetoExiste.setStatus(projetoAtualizado.getStatus());

        if (projetoAtualizado.getDataInicio() != null){
            projetoExiste.setDataInicio(projetoAtualizado.getDataInicio());
        }

        projetosService.atualizarDadosProjeto(projetoExiste);

        return ResponseEntity.ok("Projeto atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProjeto(@PathVariable int id){
        GuRi_Projetos projetoExiste = projetosService.buscarProjetoPorID(id);

        if(projetoExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto nao encontrado com projetoId " + id);
        }

        projetoExiste.setDataFim(LocalDate.now());

        projetosService.removeProjeto(projetoExiste.getProjetoId());
        return ResponseEntity.ok("Projeto removido com sucesso");
    }
}
