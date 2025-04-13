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
    private PersonagemRepository personagemRepository;
    @Autowired
    private ItemMagicoRepository itemRepository;

    public Personagem create(Personagem personagem) throws Exception {
        if (personagem.getForca() + personagem.getDefesa() != 10) {
            throw new Exception("A soma dos atributos de Força e Defesa devem ser 10");
        }

        List<Long> idItem = personagem.getItensMagicos().stream().map(ItemMagico::getId).toList();
        List<ItemMagico> itens = itemRepository.findAllById(idItem);

        Long countAmuletos = itens.stream().filter(item -> item.getTipoItem() == EnumItensMagicos.AMULETO).count();

        if (countAmuletos > 1) {
            throw new Exception("O personagem só pode ter um item do tipo Amuleto");
        }

        personagem.setItensMagicos(itens);
        return personagemRepository.save(personagem);
    }

    public List<Personagem> findAll() {

        List<Personagem> personagem = personagemRepository.findAll();

        for (Personagem p : personagem) {
            p.setForca(calculaForcaTotal(p));
            p.setDefesa(calculaDefesaTotal(p));
        }
        return personagem;
    }

    public Personagem findById(Long id) {
        Personagem personagem = personagemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado!"));

        personagem.setForca(calculaForcaTotal(personagem));
        personagem.setDefesa(calculaDefesaTotal(personagem));

        return personagem;
    }

    public Personagem updateNomeAventureiro(Long id, Personagem personagem) {
        Personagem personagemBanco = personagemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado!"));

        personagemBanco.setNomeAventureiro(personagem.getNomeAventureiro());
        return personagemRepository.save(personagemBanco);
    }

    public void delete(Long id) {
        personagemRepository.deleteById(id);
    }

    public Personagem addItemMagico(Long personagemId, Long itemMagicoId) throws Exception {
        Personagem personagem = personagemRepository.findById(personagemId).orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado!"));
        ItemMagico itemMagico = itemRepository.findById(itemMagicoId).orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));

        if (personagem.getItensMagicos().contains(itemMagico)) {
            throw new Exception("O personagem já possui este item equipado!");
        }

        if (itemMagico.getTipoItem() == EnumItensMagicos.AMULETO) {
            Long countAmuletos = personagem.getItensMagicos().stream().filter(item -> item.getTipoItem() == EnumItensMagicos.AMULETO).count();

            if (countAmuletos > 0) {
                throw new Exception("O personagem só pode possui 1 item do tipo AMULETO");
            }
        }

        personagem.getItensMagicos().add(itemMagico);

        return personagemRepository.save(personagem);
    }

    public Personagem removeItemMagico(Long personagemId, Long itemMagicoId) throws Exception {
        Personagem personagem = personagemRepository.findById(personagemId).orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado"));
        ItemMagico itemMagico = itemRepository.findById(itemMagicoId).orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));

        if (!personagem.getItensMagicos().contains(itemMagico)) {
            throw new Exception("O personagem não possui este item");
        }

        personagem.getItensMagicos().remove(itemMagico);

        return personagemRepository.save(personagem);
    }

    public ItemMagico findAmuleto(Long personagemId) throws Exception {
        Personagem personagem = findById(personagemId);
        return personagem.getItensMagicos().stream().filter(item -> item.getTipoItem() == EnumItensMagicos.AMULETO).findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado!"));
    }

    public List<ItemMagico> findItemMagico(Long personagemId) {
        Personagem personagem = findById(personagemId);
        List<ItemMagico> itens = personagem.getItensMagicos();
        return itens;
    }

    private int calculaForcaTotal(Personagem personagem) {
        int forca = personagem.getForca();
        int forcaItem = personagem.getItensMagicos().stream().mapToInt(ItemMagico::getForca).sum();
        return forca + forcaItem;
    }

    private int calculaDefesaTotal(Personagem personagem) {
        int defesa = personagem.getDefesa();
        int defesaItem = personagem.getItensMagicos().stream().mapToInt(ItemMagico::getDefesa).sum();
        return defesa + defesaItem;
    }

}
