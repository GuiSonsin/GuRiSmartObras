package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Tarefas;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_TarefasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class GuRi_TarefasController {
    GuRi_TarefasService tarefasService;

    public GuRi_TarefasController(GuRi_TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @GetMapping
    public List<GuRi_Tarefas> listarTarefas(){
        return tarefasService.listarTarefas();
    }

    @PostMapping
    public ResponseEntity<GuRi_Tarefas> criarTarefa(@RequestBody GuRi_Tarefas tarefa){
        GuRi_Tarefas tarefaCriada = tarefasService.cadastrarTarefa(tarefa);
        return ResponseEntity.ok(tarefaCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuRi_Tarefas> atualizarTarefa(@PathVariable int id, @RequestBody GuRi_Tarefas tarefaAtualizada){
        GuRi_Tarefas tarefaExiste = tarefasService.buscarTarefaPorID(id);

        if (tarefaExiste == null){
            return ResponseEntity.notFound().build();
        }

        tarefaExiste.setNome(tarefaAtualizada.getNome());
        tarefaExiste.setDescricao(tarefaAtualizada.getDescricao());
        tarefaExiste.setProjeto(tarefaAtualizada.getProjeto());
        tarefaExiste.setFuncionario(tarefaAtualizada.getFuncionario());
        tarefaExiste.setDataInicio(tarefaAtualizada.getDataInicio());

        GuRi_Tarefas tarefaAtt = tarefasService.atualizarDadosTarefa(tarefaExiste);
        return ResponseEntity.ok(tarefaAtt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GuRi_Tarefas> removeTarefa(@PathVariable int id){
        GuRi_Tarefas tarefaExiste = tarefasService.buscarTarefaPorID(id);

        if (tarefaExiste == null){
            ResponseEntity.notFound().build();
        }

        tarefaExiste.setDataFim(LocalDate.now());

        tarefasService.removeTarefa(tarefaExiste.getTarefaId());
        return ResponseEntity.ok(tarefaExiste);
    }
}
