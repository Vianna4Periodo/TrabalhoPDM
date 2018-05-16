package com.example.trabalhodaves.campeonatovideogame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Campeonato implements Serializable {
    private String id;
    private String nome;
    private String status;
    private ArrayList<Jogo> jogos;

    public ArrayList<Jogo> getJogos() {
        return jogos;
    }

    public void addJogo(Jogo jogo){
        this.jogos.add(jogo);
    }


    public Campeonato() {
        jogos = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
