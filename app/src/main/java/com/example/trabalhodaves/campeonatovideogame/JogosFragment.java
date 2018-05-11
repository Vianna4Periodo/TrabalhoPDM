package com.example.trabalhodaves.campeonatovideogame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.trabalhodaves.campeonatovideogame.adapter.JogosAdapter;
import com.example.trabalhodaves.campeonatovideogame.model.Jogo;
import com.example.trabalhodaves.campeonatovideogame.model.Time;

import java.util.ArrayList;


public class JogosFragment extends Fragment {
    private FrameLayout fragmentContainer;
    private ListView listJogos;
    private ArrayList<Jogo> jogos = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jogos, container, false);
        Jogo jogo = new Jogo();

        jogo.setTimeA(new Time());
        jogo.setTimeB(new Time());
        jogo.setPlacarTimeA(1);
        jogo.setPlacarTimeB(0);
        jogos.add(jogo);
        listJogos = view.findViewById(R.id.listJogos);
        fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_jogos);
        JogosAdapter adapter = new JogosAdapter(view.getContext(), jogos);

        listJogos.setAdapter(adapter);
        return view;
    }

    public JogosFragment(){

    }

    /**
     * Called when a fragment will be displayed
     */
    public void willBeDisplayed() {
        // Do what you want here, for example animate the content
        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }

    /**
     * Called when a fragment will be hidden
     */
    public void willBeHidden() {
        if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
            fragmentContainer.startAnimation(fadeOut);
        }
    }
}