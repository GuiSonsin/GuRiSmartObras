package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Projetos;
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
    public GuRi_ProjetosController(GuRi_ProjetosService projetosService) {
        this.projetosService = projetosService;
    }

    @GetMapping
    public List<GuRi_Projetos> listarProjetos(){
        return projetosService.listarProjetos();
    }

    @PostMapping
    public ResponseEntity<String> cadastrarProjetos(@RequestBody GuRi_Projetos projeto){
        projetosService.cadastrarProjeto(projeto);
        return  ResponseEntity.ok("Projeto cadastrado com sucesso!");
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
