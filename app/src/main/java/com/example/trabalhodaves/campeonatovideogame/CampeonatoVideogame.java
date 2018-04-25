package com.example.trabalhodaves.campeonatovideogame;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CampeonatoVideogame extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("Campeonato.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
