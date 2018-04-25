package com.example.trabalhodaves.campeonatovideogame.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Time extends RealmObject {
    private Player player;
    private RealmList<Jogador> jogadores;
}
