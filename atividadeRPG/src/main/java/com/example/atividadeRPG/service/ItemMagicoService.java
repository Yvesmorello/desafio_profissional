package com.example.atividadeRPG.service;

import com.example.atividadeRPG.enums.EnumItensMagicos;
import com.example.atividadeRPG.model.ItemMagico;
import com.example.atividadeRPG.repository.ItemMagicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemMagicoService {

    @Autowired
    private ItemMagicoRepository repository;

    public ItemMagico create(ItemMagico itemMagico) throws Exception{

        if(itemMagico.getForca() == 0 && itemMagico.getDefesa() == 0){
            throw new Exception("Um item Magico não pode ter ambos os atributos FORÇA e DEFESA valendo 0 (Zero)");
        }
        if (itemMagico.getTipoItem() == EnumItensMagicos.ARMA && itemMagico.getDefesa() > 0){
            throw new Exception("Itens do tipo ARMA não podem ter valores de DEFESA");
        }
        if (itemMagico.getTipoItem() == EnumItensMagicos.ARMA && itemMagico.getForca() > 10){
            throw new Exception("Itens do tipo ARMA só podem ter no maximo 10 pontos atirubuidos a FORÇA");
        }
        if(itemMagico.getTipoItem() == EnumItensMagicos.ARMADURA && itemMagico.getForca() > 0){
            throw new Exception("Itens do tipo ARMADURA não podem ter valores de FORÇA");
        }
        if(itemMagico.getTipoItem() == EnumItensMagicos.ARMADURA && itemMagico.getDefesa() > 10){
            throw new Exception("Itens do tipo ARMADURA só podem ter no maximo 10 pontos atirubuidos a DEFESA");
        }
        if(itemMagico.getTipoItem() == EnumItensMagicos.AMULETO && itemMagico.getForca() + itemMagico.getDefesa() > 10){
            throw new Exception("Amuletos só podem ter no maximo 10 pontos atirubuidos entre FORÇA e DEFESA");
        }

        return repository.save(itemMagico);
    }

    public List<ItemMagico> findAll(){
        return repository.findAll();
    }

    public ItemMagico findById(long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item não encontrado!"));
    }

}
