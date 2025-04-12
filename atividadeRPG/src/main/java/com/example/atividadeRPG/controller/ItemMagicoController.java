package com.example.atividadeRPG.controller;

import com.example.atividadeRPG.service.ItemMagicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoService service;
}
