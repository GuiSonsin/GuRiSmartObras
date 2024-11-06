package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Tarefas;
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
    public GuRi_TarefasController(GuRi_TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @GetMapping
    public List<GuRi_Tarefas> listarTarefas(){
        return tarefasService.listarTarefas();
    }

    @PostMapping
    public ResponseEntity<String> criarTarefa(@RequestBody GuRi_Tarefas tarefa){
        tarefasService.cadastrarTarefa(tarefa);
        return ResponseEntity.ok("Tarefa cadastrada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarTarefa(@PathVariable int id, @RequestBody GuRi_Tarefas tarefaAtualizada){
        GuRi_Tarefas tarefaExiste = tarefasService.buscarTarefaPorID(id);

        if (tarefaExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa nao encontrado com tarefaId " + id);
        }

        tarefaExiste.setNome(tarefaAtualizada.getNome());
        tarefaExiste.setDescricao(tarefaAtualizada.getDescricao());
        tarefaExiste.setProjeto(tarefaAtualizada.getProjeto());
        tarefaExiste.setFuncionario(tarefaAtualizada.getFuncionario());
        tarefaExiste.setDataInicio(tarefaAtualizada.getDataInicio());

        tarefasService.atualizarDadosTarefa(tarefaExiste);
        return ResponseEntity.ok("Tarefa atualizada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeTarefa(@PathVariable int id){
        GuRi_Tarefas tarefaExiste = tarefasService.buscarTarefaPorID(id);

        if (tarefaExiste == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa nao encontrado com tarefaId " + id);
        }

        tarefaExiste.setDataFim(LocalDate.now());

        tarefasService.removeTarefa(tarefaExiste.getTarefaId());
        return ResponseEntity.ok("Tarefa removida com sucesso!");
    }
}
