package com.example.trabalhodaves.campeonatovideogame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.trabalhodaves.campeonatovideogame.model.Player;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPlayerActivity extends AppCompatActivity {

    EditText edtNomePlayer, edtIdadePlayer;
    Button btnGravarPlayer;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        binding();

        btnGravarPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Player player = new Player();
               String nome = edtNomePlayer.getText().toString();
               int idade = Integer.parseInt(edtIdadePlayer.getText().toString());

               player.setNome(nome);
               player.setIdade(idade);
               player.setPontuacao(0);
               finish();

               DatabaseReference referencetoPlayers = database.getReference().child("Players");
               referencetoPlayers.child(player.getId()).setValue(player);
            }
        });
    }

    private void binding() {
        edtIdadePlayer = findViewById(R.id.edtIdadePlayer);
        edtNomePlayer = findViewById(R.id.edtNomePlayer);
        btnGravarPlayer = findViewById(R.id.btnGravarPlayer);

    }
}
