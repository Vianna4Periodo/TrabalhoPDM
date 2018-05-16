package com.example.trabalhodaves.campeonatovideogame.model;

import java.io.Serializable;
import java.util.UUID;

public class Jogo  implements Serializable {
    private String id;
    private Time timeA;
    private Time timeB;
    private int placarTimeA;
    private int placarTimeB;

    public Jogo(){
        this.id = UUID.randomUUID().toString();
    }

    public Jogo(Time timeA, Time timeB, int placarTimeA, int placarTimeB) {
        this.id = UUID.randomUUID().toString();
        this.timeA = timeA;
        this.timeB = timeB;
        this.placarTimeA = placarTimeA;
        this.placarTimeB = placarTimeB;
    }

    public Jogo(Time timeA, Time timeB) {
        this.id = UUID.randomUUID().toString();
        this.timeA = timeA;
        this.timeB = timeB;
        this.placarTimeA = 0;
        this.placarTimeB = 0;
    }

    public String getId() {
        return id;
    }

    public Time getTimeA() {
        return timeA;
    }

    public void setTimeA(Time timeA) {
        this.timeA = timeA;
    }

    public Time getTimeB() {
        return timeB;
    }

    public void setTimeB(Time timeB) {
        this.timeB = timeB;
    }

    public int getPlacarTimeA() {
        return placarTimeA;
    }

    public void setPlacarTimeA(int placarTimeA) {
        this.placarTimeA = placarTimeA;
    }

    public int getPlacarTimeB() {
        return placarTimeB;
    }

    public void setPlacarTimeB(int placarTimeB) {
        this.placarTimeB = placarTimeB;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
