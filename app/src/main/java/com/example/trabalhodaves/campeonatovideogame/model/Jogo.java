package com.example.trabalhodaves.campeonatovideogame.model;

import java.util.UUID;

public class Jogo {
    private String id;
    private Time timeA;
    private Time timeB;
    private int placarTimeA;
    private int placarTimeB;

    public Jogo(){
        this.id = UUID.randomUUID().toString();
    }
}
