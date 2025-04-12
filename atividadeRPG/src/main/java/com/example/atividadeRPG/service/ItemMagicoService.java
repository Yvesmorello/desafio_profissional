package com.example.atividadeRPG.service;

import com.example.atividadeRPG.enums.EnumItensMagicos;
import com.example.atividadeRPG.model.ItemMagico;
import com.example.atividadeRPG.model.Personagem;
import com.example.atividadeRPG.repository.ItemMagicoRepository;
import com.example.atividadeRPG.repository.PersonagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemMagicoService {

    @Autowired
    private ItemMagicoRepository repository;

    public ItemMagico create(ItemMagico itemMagico) throws Exception{

        if(itemMagico.getForca() == 0 && itemMagico.getDefesa() == 0){
            throw new Exception("Um itemMagico não pode ter ambos os atributos FORÇA e DEFESA valendo 0 (Zero)");
        }
        if (itemMagico.getTipoItem() == EnumItensMagicos.ARMA && itemMagico.getDefesa() > 0){
            throw new Exception("Itens do tipo ARMA não podem ter valores de DEFESA");
        }
        if(itemMagico.getTipoItem() == EnumItensMagicos.ARMADURA && itemMagico.getDefesa() > 0){
            throw new Exception("Itens do tipo ARMADURA não podem ter valores de FORÇA");
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
