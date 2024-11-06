package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Materiais;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_MateriaisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiais")
public class GuRi_MateriaisController {
    GuRi_MateriaisService materiaisService;

    public GuRi_MateriaisController(GuRi_MateriaisService materiaisService) {
        this.materiaisService = materiaisService;
    }

    @GetMapping
    public List<GuRi_Materiais> listarMateriais(){
        return materiaisService.listarMateriais();
    }

    @PostMapping
    public ResponseEntity<GuRi_Materiais> cadastrarMaterial(@RequestBody GuRi_Materiais material) throws Exception {
        if (material.getPreco() <= 0){
            throw new Exception("Preco precisa ser maior que 0");
        } else if (material.getQuantidade() <= 0) {
            throw new Exception("Quantidade precisa ser maior que 0");
        }
        GuRi_Materiais materialCriado = materiaisService.cadastrarMaterial(material);
        return ResponseEntity.ok(materialCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuRi_Materiais> atualizarMaterial(@PathVariable int id, @RequestBody GuRi_Materiais materialAtualizado) throws Exception {
        GuRi_Materiais materialExiste = materiaisService.buscarMaterialPorID(id);

        if (materialExiste == null){
            return ResponseEntity.notFound().build();
        }

        if (materialAtualizado.getPreco() <= 0){
            throw new Exception("Preco precisa ser maior que 0");
        } else if (materialAtualizado.getQuantidade() <= 0) {
            throw new Exception("Quantidade precisa ser maior que 0");
        }
        materialExiste.setNome(materialAtualizado.getNome());
        materialExiste.setPreco(materialAtualizado.getPreco());
        materialExiste.setQuantidade(materialAtualizado.getQuantidade());

        GuRi_Materiais materialAtt = materiaisService.atualizarDadosMaterial(materialExiste);
        return ResponseEntity.ok(materialAtt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GuRi_Materiais> removeMaterial(@PathVariable int id){
        GuRi_Materiais materialExiste = materiaisService.buscarMaterialPorID(id);

        if (materialExiste == null){
            ResponseEntity.notFound().build();
        }

        materiaisService.removeMaterial(materialExiste.getMaterialId());
        return ResponseEntity.ok(materialExiste);
    }
}
