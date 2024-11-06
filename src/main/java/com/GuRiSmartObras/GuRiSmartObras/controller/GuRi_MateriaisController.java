package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Materiais;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_MateriaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiais")
public class GuRi_MateriaisController {
    GuRi_MateriaisService materiaisService;

    @Autowired
    public GuRi_MateriaisController(GuRi_MateriaisService materiaisService) {
        this.materiaisService = materiaisService;
    }

    @GetMapping
    public List<GuRi_Materiais> listarMateriais(){
        return materiaisService.listarMateriais();
    }

    @PostMapping
    public ResponseEntity<String> cadastrarMaterial(@RequestBody GuRi_Materiais material)  {
        if (material.getPreco() <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Preco precisa ser maior que 0");
        }
        if (material.getQuantidade() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quantidade precisa ser maior que 0");
        }

        materiaisService.cadastrarMaterial(material);
        return ResponseEntity.ok("Material cadastrado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarMaterial(@PathVariable int id, @RequestBody GuRi_Materiais materialAtualizado) {
        GuRi_Materiais materialExiste = materiaisService.buscarMaterialPorID(id);

        if (materialExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material nao encontrado com materialId " + id);
        }

        if (materialAtualizado.getPreco() <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Preco precisa ser maior que 0");
        } else if (materialAtualizado.getQuantidade() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quantidade precisa ser maior que 0");
        }
        materialExiste.setNome(materialAtualizado.getNome());
        materialExiste.setPreco(materialAtualizado.getPreco());
        materialExiste.setQuantidade(materialAtualizado.getQuantidade());

        materiaisService.atualizarDadosMaterial(materialExiste);
        return ResponseEntity.ok("Material atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeMaterial(@PathVariable int id){
        GuRi_Materiais materialExiste = materiaisService.buscarMaterialPorID(id);

        if (materialExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material nao encontrado com materialId " + id);
        }

        materiaisService.removeMaterial(materialExiste.getMaterialId());
        return ResponseEntity.ok("Material removido com sucesso!");
    }
}
