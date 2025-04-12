package com.example.atividadeRPG.service;

import com.example.atividadeRPG.enums.EnumItensMagicos;
import com.example.atividadeRPG.model.ItemMagico;
import com.example.atividadeRPG.model.Personagem;
import com.example.atividadeRPG.repository.ItemMagicoRepository;
import com.example.atividadeRPG.repository.PersonagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository repository;
    @Autowired
    private ItemMagicoRepository itemRepository;

    public Personagem create(Personagem personagem) throws Exception {
        if(personagem.getForca() + personagem.getDefesa() != 10){
            throw new Exception("A soma dos atributos de Força e Defesa devem ser 10");
        }

        List<ItemMagico> itens = personagem.getItensMagicos();
        Long countAmuletos = itens.stream().filter(item -> item.getTipoItem() == EnumItensMagicos.AMULETO).count();

        if(countAmuletos > 1){
            throw new Exception("O personagem só pode ter um item do tipo Amuleto");
        }
/*
        for(ItemMagico item : itens){
            if(item.getForca() == 0 && item.getDefesa() == 0){
                throw new Exception("Um item não pode ter ambos os atributos FORÇA e DEFESA valendo 0 (Zero)");
            }
            if (item.getTipoItem() == EnumItensMagicos.ARMA && item.getDefesa() > 0){
                throw new Exception("Itens do tipo ARMA não podem ter valores de DEFESA");
            }
            if(item.getTipoItem() == EnumItensMagicos.ARMADURA && item.getDefesa() > 0){
                throw new Exception("Itens do tipo ARMADURA não podem ter valores de FORÇA");
            }
        }*/
        return repository.save(personagem);
    }

    public List<Personagem> findAll(){
        return repository.findAll();
    }

    public Personagem findById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado!"));
    }

    public Personagem updateNomeAventureiro(Long id, Personagem personagem){
        personagem = findById(id);
        personagem.setNomeAventureiro(personagem.getNomeAventureiro());
        return repository.save(personagem);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Personagem addItemMagico(Long personagemId, Long itemMagicoId) throws Exception {
        Personagem personagem = findById(personagemId);
        ItemMagico itemMagico = itemRepository.findById(itemMagicoId).orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));

        if(personagem.getItensMagicos().contains(itemMagico)){
            throw new Exception("O personagem já possui este item equipado!");
        }

        if(itemMagico.getTipoItem() == EnumItensMagicos.AMULETO){
            Long countAmuletos = personagem.getItensMagicos().stream().filter(item -> item.getTipoItem() == EnumItensMagicos.AMULETO).count();

            if(countAmuletos > 0){
                throw new Exception("O personagem só pode possui 1 item do tipo AMULETO");
            }
        }

        personagem.getItensMagicos().add(itemMagico);
        return repository.save(personagem);
    }

    public Personagem removeItemMagico(Long personagemId, Long itemMagicoId) throws Exception {
        Personagem personagem = findById(personagemId);
        ItemMagico itemMagico = itemRepository.findById(itemMagicoId).orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));

        if(!personagem.getItensMagicos().contains(itemMagico)){
            throw new Exception("O personagem não possui este item");
        }

        personagem.getItensMagicos().remove(itemMagico);
        return repository.save(personagem);
    }

    public ItemMagico findAmuleto(Long personagemId) throws Exception {
        Personagem personagem = findById(personagemId);
        return personagem.getItensMagicos().stream().filter(item -> item.getTipoItem() == EnumItensMagicos.AMULETO).findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado!"));
    }

    public List<ItemMagico> findItemMagico(Long personagemId){
        Personagem personagem = findById(personagemId);
        List<ItemMagico> itens = personagem.getItensMagicos();
        return itens;
    }
}
