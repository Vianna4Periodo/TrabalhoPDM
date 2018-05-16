package com.example.trabalhodaves.campeonatovideogame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Campeonato implements Serializable {
    private String id;
    private ArrayList<Jogo> jogos;

    public ArrayList<Jogo> getJogos() {
        return jogos;
    }

    public void addJogo(Jogo jogo){
        this.jogos.add(jogo);
    }

    public Campeonato(String id){
        jogos = new ArrayList<>();
        this.id = id;
    }

    public Campeonato() {
        jogos = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
