package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Projetos;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_ProjetosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/projetos")
public class GuRi_ProjetosController {
    GuRi_ProjetosService projetosService;

    public GuRi_ProjetosController(GuRi_ProjetosService projetosService) {
        this.projetosService = projetosService;
    }

    @GetMapping
    public List<GuRi_Projetos> listarProjetos(){
        return projetosService.listarProjetos();
    }

    @PostMapping
    public ResponseEntity<GuRi_Projetos> cadastrarProjetos(@RequestBody GuRi_Projetos projeto){
        GuRi_Projetos projetoCriado = projetosService.cadastrarProjeto(projeto);
        return  ResponseEntity.ok(projetoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuRi_Projetos> atualizarProjeto(@PathVariable int id, @RequestBody GuRi_Projetos projetoAtualizado){
        GuRi_Projetos projetoExiste = projetosService.buscarProjetoPorID(id);

        if (projetoExiste == null){
            ResponseEntity.notFound().build();
        }

        projetoExiste.setNome(projetoAtualizado.getNome());
        projetoExiste.setCliente(projetoAtualizado.getCliente());
        projetoExiste.setDescricao(projetoAtualizado.getDescricao());
        projetoExiste.setFuncionario(projetoAtualizado.getFuncionario());
        projetoExiste.setStatus(projetoAtualizado.getStatus());

        if (projetoAtualizado.getDataInicio() != null){
            projetoExiste.setDataInicio(projetoAtualizado.getDataInicio());
        }

        GuRi_Projetos projetoAtt = projetosService.atualizarDadosProjeto(projetoExiste);
        return ResponseEntity.ok(projetoAtt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GuRi_Projetos> removeProjeto(@PathVariable int id){
        GuRi_Projetos projetoExiste = projetosService.buscarProjetoPorID(id);

        if(projetoExiste == null){
            return ResponseEntity.notFound().build();
        }

        projetoExiste.setDataFim(LocalDate.now());

        projetosService.removeProjeto(projetoExiste.getProjetoId());
        return ResponseEntity.ok(projetoExiste);
    }
}
