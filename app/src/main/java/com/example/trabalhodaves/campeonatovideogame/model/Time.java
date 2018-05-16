package com.example.trabalhodaves.campeonatovideogame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Time implements Serializable {
    private String id;
    private Player player;
    private String nome;
    private List<Jogador> jogadores = new ArrayList<Jogador>();

    public Time() {
        this.id = UUID.randomUUID().toString();
        this.player = null;
        this.nome = null;
    }

    public Time(Player player, String nome) {
        this.id = UUID.randomUUID().toString();
        this.player = player;
        this.nome = nome;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Time)) return false;

        Time time = (Time) o;

        if (getId() != null ? !getId().equals(time.getId()) : time.getId() != null) return false;
        if (getPlayer() != null ? !getPlayer().equals(time.getPlayer()) : time.getPlayer() != null)
            return false;
        if (getNome() != null ? !getNome().equals(time.getNome()) : time.getNome() != null)
            return false;
        return getJogadores() != null ? getJogadores().equals(time.getJogadores()) : time.getJogadores() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPlayer() != null ? getPlayer().hashCode() : 0);
        result = 31 * result + (getNome() != null ? getNome().hashCode() : 0);
        result = 31 * result + (getJogadores() != null ? getJogadores().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

}
