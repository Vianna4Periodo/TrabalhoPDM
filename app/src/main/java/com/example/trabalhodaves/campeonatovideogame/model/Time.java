package com.example.trabalhodaves.campeonatovideogame.model;

import java.util.List;
import java.util.UUID;

public class Time{
    private String id;
    private Player player;
    private String nome;
    private List<Jogador> jogadores;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public Time(){
        this.id = UUID.randomUUID().toString();
    }
}
