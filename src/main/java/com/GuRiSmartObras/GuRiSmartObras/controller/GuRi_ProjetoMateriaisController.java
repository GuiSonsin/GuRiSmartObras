package com.GuRiSmartObras.GuRiSmartObras.controller;

import com.GuRiSmartObras.GuRiSmartObras.dto.GuRi_ProjetoMateriaisRequest;
import com.GuRiSmartObras.GuRiSmartObras.dto.GuRi_ResponseMessage;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Materiais;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_ProjetoMateriais;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_ProjetoMateriaisID;
import com.GuRiSmartObras.GuRiSmartObras.model.GuRi_Projetos;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_MateriaisService;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_ProjetoMateriaisService;
import com.GuRiSmartObras.GuRiSmartObras.service.GuRi_ProjetosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetomateriais")
public class GuRi_ProjetoMateriaisController {
    @Autowired
    GuRi_ProjetoMateriaisService projetoMateriaisService;
    @Autowired
    GuRi_MateriaisService materiaisService;
    @Autowired
    GuRi_ProjetosService projetosService;

    @Autowired
    public GuRi_ProjetoMateriaisController(GuRi_ProjetoMateriaisService projetoMateriaisService) {
        this.projetoMateriaisService = projetoMateriaisService;
    }

    @GetMapping
    public List<GuRi_ProjetoMateriais> listarProjetoMaterias(){
        return projetoMateriaisService.listarProjetoMateriais();
    }

    @PostMapping
    public ResponseEntity<GuRi_ResponseMessage> cadastrarProjetoMateriais(@RequestBody GuRi_ProjetoMateriaisRequest projetoMateriais) {
        GuRi_Materiais material = materiaisService.buscarMaterialPorID(projetoMateriais.getMaterialId());

        if (material == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Material nao encontrado com materialId " + projetoMateriais.getMaterialId()));
        }

        GuRi_Projetos projeto = projetosService.buscarProjetoPorID(projetoMateriais.getProjetoId());

        if (projeto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Projeto nao encontrado com projetoId " + projetoMateriais.getProjetoId()));
        }

        if(projetoMateriais.getQuantidade() <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GuRi_ResponseMessage("Quantidade precisa ser maior que 0"));
        }

        GuRi_ProjetoMateriaisID idProjetoMaterialComposta = new GuRi_ProjetoMateriaisID(projetoMateriais.getProjetoId(), projetoMateriais.getMaterialId());

        GuRi_ProjetoMateriais projetoMaterial = new GuRi_ProjetoMateriais(idProjetoMaterialComposta, projetoMateriais.getQuantidade());
        projetoMaterial.setMaterial(material);
        projetoMaterial.setProjeto(projeto);

        projetoMateriaisService.cadastrarProjetoMaterial(projetoMaterial);

        return ResponseEntity.ok(new GuRi_ResponseMessage("Projeto Materiais cadastrado com sucesso!"));
    }

    @PutMapping("/{projetoId}/{materialId}")
    public ResponseEntity<GuRi_ResponseMessage> atualizarProjetoMateriais(@PathVariable int projetoId,
                @PathVariable int materialId, @RequestBody GuRi_ProjetoMateriaisRequest projetoMaterialAtualizado) {

        GuRi_ProjetoMateriais projetoMaterialExiste = projetoMateriaisService.buscarProjetoMaterialPorID(projetoId, materialId);

        if (projetoMaterialExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Projeto Material com projetoId " + projetoId + " e materialId " + materialId +
                    " nao encontrado!"));
        }

        GuRi_Projetos novoProjeto = projetosService.buscarProjetoPorID(projetoMaterialAtualizado.getProjetoId());
        GuRi_Materiais novoMaterial = materiaisService.buscarMaterialPorID(projetoMaterialAtualizado.getMaterialId());

        if (projetoMaterialAtualizado.getQuantidade() <= 0 ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GuRi_ResponseMessage("Quantidade precisa ser maior que 0"));
        }

        projetoMaterialExiste.setQuantidade(projetoMaterialAtualizado.getQuantidade());
        projetoMaterialExiste.setProjeto(novoProjeto);
        projetoMaterialExiste.setMaterial(novoMaterial);

        projetoMateriaisService.atualizarDadosProjetoMaterial(projetoMaterialExiste);
        return ResponseEntity.ok(new GuRi_ResponseMessage("Projeto Materiais atualizado com sucesso!"));
    }

    @DeleteMapping("/{projetoId}/{materialId}")
    public ResponseEntity<GuRi_ResponseMessage> removeProjetoMateriais(@PathVariable int projetoId, @PathVariable int materialId){
        GuRi_ProjetoMateriais projetoMaterialExiste = projetoMateriaisService.buscarProjetoMaterialPorID(projetoId, materialId);

        if (projetoMaterialExiste == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GuRi_ResponseMessage("Projeto Material com projetoId " + projetoId + " e materialId " + materialId
                    + " nao encontrado!"));
        }

        projetoMateriaisService.removeProjetoMaterial(projetoId, materialId);

        return ResponseEntity.ok(new GuRi_ResponseMessage("Projeto Materiais removido com sucesso!"));
    }
}