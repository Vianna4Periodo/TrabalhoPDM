package com.example.trabalhodaves.campeonatovideogame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trabalhodaves.campeonatovideogame.R;
import com.example.trabalhodaves.campeonatovideogame.model.Jogo;

import java.util.ArrayList;
import java.util.List;

public class JogosAdapter extends BaseAdapter {

    private final Context context;
    private List<Jogo> jogos;

    public JogosAdapter(Context context, ArrayList<Jogo> jogos) {
        this.context = context;
        this.jogos = jogos;
    }

    @Override
    public int getCount() {
        if(jogos != null)
        return jogos.size();
        return 0;
    }

    @Override
    public Jogo getItem(int position) {
        return jogos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.lista_jogos, null);
        }

        Jogo jogo = jogos.get(position);


        if(jogo != null){
            TextView time1 = v.findViewById(R.id.time_1);
            TextView time2 = v.findViewById(R.id.time_2);
            TextView pontos1 = v.findViewById(R.id.placarTime1);
            TextView pontos2= v.findViewById(R.id.placarTime2);

            time1.setText(jogo.getTimeA().getNome());
            time2.setText(jogo.getTimeB().getNome());
            pontos1.setText(String.valueOf(jogo.getPlacarTimeA()));
            pontos2.setText(String.valueOf(jogo.getPlacarTimeB()));
        }
        return v;
    }


}
