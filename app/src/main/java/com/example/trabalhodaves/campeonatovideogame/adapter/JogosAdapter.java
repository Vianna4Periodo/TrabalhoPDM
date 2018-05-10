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

    private final ArrayList<Jogo> jogos = new ArrayList<>();
    private final Context context;


    public JogosAdapter(Context context, ArrayList<Jogo> lista) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(context);
        view.setText("Item "+position);
        return null;
    }
}
