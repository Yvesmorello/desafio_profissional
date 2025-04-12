package com.example.atividadeRPG.model;

import com.example.atividadeRPG.enums.EnumItensMagicos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
@AllArgsConstructor
@NoArgsConstructor
@Data*/
@Entity
@Table(name = "TB_ITEMMAGICO")
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private EnumItensMagicos tipoItem;
    private Integer forca;
    private Integer defesa;

    public ItemMagico(Long id, String nome, EnumItensMagicos tipoItem, Integer forca, Integer defesa) {
        this.id = id;
        this.nome = nome;
        this.tipoItem = tipoItem;
        this.forca = forca;
        this.defesa = defesa;
    }

    public ItemMagico() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnumItensMagicos getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(EnumItensMagicos tipoItem) {
        this.tipoItem = tipoItem;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
    }
}
