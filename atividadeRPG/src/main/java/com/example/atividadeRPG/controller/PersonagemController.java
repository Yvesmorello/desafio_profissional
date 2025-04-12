package com.example.atividadeRPG.controller;

import com.example.atividadeRPG.model.ItemMagico;
import com.example.atividadeRPG.model.Personagem;
import com.example.atividadeRPG.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {

    @Autowired
    private PersonagemService service;

    @PostMapping
    public Personagem create(@RequestBody Personagem personagem) throws Exception {
        return service.create(personagem);
    }

    @GetMapping
    public List<Personagem> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Personagem getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personagem> updateNomeAventureiro(@PathVariable Long id, @RequestBody Personagem personagem){
        return ResponseEntity.ok(service.updateNomeAventureiro(id, personagem));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{personagemId}/adicionar/itemId")
    public Personagem addItem(@PathVariable Long personagemId, @PathVariable Long itemId) throws Exception{
        return service.addItemMagico(personagemId, itemId);
    }

    @PutMapping("/{personagemId}/remover-item/{itemId}")
    public Personagem removerItem(@PathVariable Long personagemId, @PathVariable Long itemId) throws Exception {
        return service.removeItemMagico(personagemId, itemId);
    }

    @GetMapping("/{personagemId}/amuleto")
    public ItemMagico buscarAmuleto(@PathVariable Long personagemId) throws Exception {
        return service.findAmuleto(personagemId);
    }

    @GetMapping("/itens/{id}")
    public List<ItemMagico> getItens(@PathVariable Long id){
        return service.findItemMagico(id);
    }
}
