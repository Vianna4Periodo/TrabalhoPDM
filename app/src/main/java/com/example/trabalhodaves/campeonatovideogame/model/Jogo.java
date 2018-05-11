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


}
