package com.example.trabalhodaves.campeonatovideogame.model;

import java.io.Serializable;
import java.util.UUID;

public class Campeonato implements Serializable {
    private String id;
    private String nome;
    private String status;

    public Campeonato() {
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
