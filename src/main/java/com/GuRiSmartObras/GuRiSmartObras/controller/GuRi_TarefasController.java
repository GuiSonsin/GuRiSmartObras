package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.dto.GuRi_ResponseMessage;
import com.GuRiSmartObras.GuRiSmartObras.dto.GuRi_TarefaRequest;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Funcionarios;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Projetos;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Tarefas;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_FuncionariosService;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_ProjetosService;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_TarefasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class GuRi_TarefasController {
    GuRi_TarefasService tarefasService;

    @Autowired
    GuRi_ProjetosService projetosService;

    @Autowired
    GuRi_FuncionariosService funcionariosService;

    @Autowired
    public GuRi_TarefasController(GuRi_TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @GetMapping
    public List<GuRi_Tarefas> listarTarefas(){
        return tarefasService.listarTarefas();
    }

    @PostMapping
    public ResponseEntity<GuRi_ResponseMessage> criarTarefa(@RequestBody GuRi_TarefaRequest tarefa){
        if (tarefa.getNome() == null || tarefa.getDescricao() == null) {
            return ResponseEntity.badRequest().body(new GuRi_ResponseMessage("Informe todos os dados obrigatório para criar a tarefa (Nome, Descrição)"));
        }

        GuRi_Projetos projeto = projetosService.buscarProjetoPorID(tarefa.getProjetoId());

        if (projeto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Projeto com ID " + tarefa.getProjetoId() + " não foi encontrado!"));
        }

        GuRi_Funcionarios funcionario = funcionariosService.buscarFuncionarioPorID(tarefa.getFuncionarioId());

        if (funcionario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Funcionário com ID " + tarefa.getFuncionarioId() + " não foi encontrado!"));
        }

        GuRi_Tarefas tarefaCriada = new GuRi_Tarefas(
                tarefa.getNome(),
                tarefa.getDescricao(),
                tarefa.getDataInicio(),
                tarefa.getDataFim(),
                projeto,
                funcionario
        );

        tarefasService.cadastrarTarefa(tarefaCriada);
        return ResponseEntity.ok(new GuRi_ResponseMessage("Tarefa cadastrada com sucesso!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuRi_ResponseMessage> atualizarTarefa(@PathVariable int id, @RequestBody GuRi_TarefaRequest tarefaAtualizada){
        GuRi_Tarefas tarefaExiste = tarefasService.buscarTarefaPorID(id);

        if (tarefaExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Tarefa nao encontrado com tarefaId " + id));
        }

        GuRi_Projetos projeto = projetosService.buscarProjetoPorID(tarefaExiste.getProjeto().getProjetoId());

        if (projeto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Projeto com ID " + tarefaAtualizada.getProjetoId() + " não foi encontrado!"));
        }

        GuRi_Funcionarios funcionario = funcionariosService.buscarFuncionarioPorID(tarefaExiste.getFuncionario().getFuncionarioId());

        if (funcionario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Funcionário com ID " + tarefaAtualizada.getFuncionarioId() + " não foi encontrado!"));
        }

        tarefaExiste.setNome(tarefaAtualizada.getNome() == null ? tarefaExiste.getNome() : tarefaAtualizada.getNome());
        tarefaExiste.setDescricao(tarefaAtualizada.getDescricao()  == null ? tarefaExiste.getDescricao() : tarefaAtualizada.getDescricao());
        tarefaExiste.setProjeto(tarefaAtualizada.getProjetoId() == 0 ? tarefaExiste.getProjeto() : projeto);
        tarefaExiste.setFuncionario(tarefaAtualizada.getFuncionarioId() == 0 ? tarefaExiste.getFuncionario() : funcionario);
        tarefaExiste.setDataInicio(tarefaAtualizada.getDataInicio() == null ? tarefaExiste.getDataInicio() : tarefaAtualizada.getDataInicio());

        tarefasService.atualizarDadosTarefa(tarefaExiste);
        return ResponseEntity.ok(new GuRi_ResponseMessage("Tarefa atualizada com sucesso!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GuRi_ResponseMessage> removeTarefa(@PathVariable int id){
        GuRi_Tarefas tarefaExiste = tarefasService.buscarTarefaPorID(id);

        if (tarefaExiste == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Tarefa nao encontrado com tarefaId " + id));
        }

        tarefaExiste.setDataFim(LocalDate.now());

        tarefasService.removeTarefa(tarefaExiste.getTarefaId());
        return ResponseEntity.ok(new GuRi_ResponseMessage("Tarefa removida com sucesso!"));
    }
}
