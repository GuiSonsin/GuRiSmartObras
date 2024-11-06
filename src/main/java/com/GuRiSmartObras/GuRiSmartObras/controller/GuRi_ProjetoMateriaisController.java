package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_ProjetoMateriais;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_ProjetoMateriaisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetomateriais")
public class GuRi_ProjetoMateriaisController {
    GuRi_ProjetoMateriaisService projetoMateriaisService;

    public GuRi_ProjetoMateriaisController(GuRi_ProjetoMateriaisService projetoMateriaisService) {
        this.projetoMateriaisService = projetoMateriaisService;
    }

    @GetMapping
    public List<GuRi_ProjetoMateriais> listarProjetoMaterias(){
        return projetoMateriaisService.listarProjetoMateriais();
    }

    @PostMapping
    public ResponseEntity<GuRi_ProjetoMateriais> cadastrarProjetoMateriais(@RequestBody GuRi_ProjetoMateriais projetoMateriais){
        GuRi_ProjetoMateriais projetoMaterialCriado =  projetoMateriaisService.cadastrarProjetoMaterial(projetoMateriais);
        return ResponseEntity.ok(projetoMaterialCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuRi_ProjetoMateriais> atualizarProjetoMateriais(@PathVariable int id, @RequestBody GuRi_ProjetoMateriais projetoMaterialAtualizado){
        GuRi_ProjetoMateriais projetoMaterialExiste = projetoMateriaisService.buscarProjetoMaterialPorID(id);

        if (projetoMaterialExiste == null){
            ResponseEntity.notFound().build();
        }

        projetoMaterialExiste.setProjeto(projetoMaterialAtualizado.getProjeto());
        projetoMaterialExiste.setMaterial(projetoMaterialAtualizado.getMaterial());
        projetoMaterialExiste.setQuantidade(projetoMaterialAtualizado.getQuantidade());

        GuRi_ProjetoMateriais projetoMateriaisAtt = projetoMateriaisService.atualizarDadosProjetoMaterial(projetoMaterialExiste);
        return ResponseEntity.ok(projetoMateriaisAtt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GuRi_ProjetoMateriais> removeProjetoMateriais(@PathVariable int id){
        GuRi_ProjetoMateriais projetoMaterialExiste = projetoMateriaisService.buscarProjetoMaterialPorID(id);

        if (projetoMaterialExiste == null){
            ResponseEntity.notFound().build();
        }

        projetoMateriaisService.removeProjetoMaterial(projetoMaterialExiste.getId().getMaterialId());
        projetoMateriaisService.removeProjetoMaterial(projetoMaterialExiste.getId().getProjetoId());
        return ResponseEntity.ok(projetoMaterialExiste);
    }
}