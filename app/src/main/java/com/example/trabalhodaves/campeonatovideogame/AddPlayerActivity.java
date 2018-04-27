package com.example.trabalhodaves.campeonatovideogame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.trabalhodaves.campeonatovideogame.model.Player;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;

public class AddPlayerActivity extends AppCompatActivity {

    EditText edtNomePlayer, edtIdadePlayer;
    Button btnGravarPlayer;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        binding();

        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm){
                List<Player> players = realm.where(Player.class).findAll();
                int i = players.size();
            }
        });
        btnGravarPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.executeTransaction(new Realm.Transaction(){
                    @Override
                    public void execute(Realm realm) {
                        Player player = realm.createObject(Player.class, UUID.randomUUID());
                        player.setNome(edtNomePlayer.getText().toString());
                        player.setIdade(Integer.parseInt(edtIdadePlayer.getText().toString()));
                        player.setPontuacao(0);
                    }
                });
            }
        });
    }

    private void binding() {
        edtIdadePlayer = findViewById(R.id.edtIdadePlayer);
        edtNomePlayer = findViewById(R.id.edtNomePlayer);
        btnGravarPlayer = findViewById(R.id.btnGravarPlayer);
        realm = Realm.getDefaultInstance();
    }
}
