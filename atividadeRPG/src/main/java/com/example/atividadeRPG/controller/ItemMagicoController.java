package com.example.atividadeRPG.controller;

import com.example.atividadeRPG.model.ItemMagico;
import com.example.atividadeRPG.service.ItemMagicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoService service;

    @PostMapping
    public ItemMagico create(@RequestBody ItemMagico item) throws Exception {
        return service.create(item);
    }

    @GetMapping
    public List<ItemMagico> getAll() throws Exception {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ItemMagico getById(@PathVariable Long id) throws Exception {
        return service.findById(id);
    }
}
