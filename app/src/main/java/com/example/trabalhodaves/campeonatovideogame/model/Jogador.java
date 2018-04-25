package com.example.trabalhodaves.campeonatovideogame.model;

import io.realm.RealmObject;

public class Jogador extends RealmObject {
    private String nome;
    private int golsMarcados;
    private Time time;
}
