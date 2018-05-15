package com.example.trabalhodaves.campeonatovideogame.model;

import java.io.Serializable;
import java.util.UUID;

public class Player implements Serializable {

    private String id;
    private String nome;
    private int idade;
    private int pontuacao;

    public Player(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public String toString(){
        return this.nome+" - "+pontuacao+" pontos ";
    }
}
