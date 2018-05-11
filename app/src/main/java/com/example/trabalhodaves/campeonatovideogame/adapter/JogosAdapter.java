package com.example.trabalhodaves.campeonatovideogame.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trabalhodaves.campeonatovideogame.model.Jogo;

import java.lang.reflect.Array;
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
        return jogos.size();
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
        TextView view = new TextView(context);
        view.setText("View da posicao "+position);
        return view;
    }
}
