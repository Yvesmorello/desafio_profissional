package com.example.atividadeRPG.model;

import com.example.atividadeRPG.enums.EnumClasses;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
/*
@AllArgsConstructor
@NoArgsConstructor
@Data*/
@Entity
@Table(name = "TB_PERSONAGEM")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String nomeAventureiro;
    @Enumerated(EnumType.STRING)
    private EnumClasses classe;
    private Integer level;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "personagem_id")
    private List<ItemMagico> itensMagicos = new ArrayList<>();
    private Integer forca;
    private Integer defesa;

    public Personagem(Long id, String nome, String nomeAventureiro, EnumClasses classe, Integer level, List<ItemMagico> itensMagicos, Integer forca, Integer defesa) {
        this.id = id;
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.itensMagicos = itensMagicos;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Personagem() {
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

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public EnumClasses getClasse() {
        return classe;
    }

    public void setClasse(EnumClasses classe) {
        this.classe = classe;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<ItemMagico> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<ItemMagico> itensMagicos) {
        this.itensMagicos = itensMagicos;
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
