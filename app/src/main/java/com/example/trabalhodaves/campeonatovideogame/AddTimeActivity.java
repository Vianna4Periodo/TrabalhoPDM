package com.example.trabalhodaves.campeonatovideogame;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class AddTimeActivity extends AppCompatActivity {

    ListView jogadores;
    Button btnGravar, btnAddJogador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time);
        binding();

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnAddJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(AddTimeActivity.this);
                AlertDialog alert = new AlertDialog.Builder(AddTimeActivity.this)
                        .setTitle("Adicionar Jogador")
                        .setView(editText)
                        .setPositiveButton("Adiciona", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton("Cancel", null)
                        .create();
            }
        });
    }

    private void binding() {
        jogadores = findViewById(R.id.listViewJogadores);
        btnGravar = findViewById(R.id.btnGravarTime);
        btnAddJogador = findViewById(R.id.btnAdicionaJogadorTime);
    }
}
