package com.example.trabalhodaves.campeonatovideogame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.trabalhodaves.campeonatovideogame.adapter.JogosAdapter;
import com.example.trabalhodaves.campeonatovideogame.model.Jogo;
import com.example.trabalhodaves.campeonatovideogame.model.Player;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class JogosFragment extends Fragment {
    private View view;
    private FrameLayout fragmentContainer;
    private ListView listJogos;
    private ArrayList<Jogo> jogos = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jogos, container, false);



        return view;
    }

    @Override
    public void onResume() {


        super.onResume();

    }

    public JogosFragment(){

    }

    /**
     * Called when a fragment will be displayed
     */
    public void willBeDisplayed() {
        // Do what you want here, for example animate the content
        NavigationActivity activity = (NavigationActivity) this.getActivity();
        jogos = activity.campeonato.getJogos();
        listJogos = view.findViewById(R.id.listJogos);
        fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_jogos);
        JogosAdapter adapter = new JogosAdapter(view.getContext(), jogos);

        listJogos.setAdapter(adapter);
        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }

        listJogos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
                Context context = v.getContext();
                final LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

// Add a TextView here for the "Title" label, as noted in the comments
                final EditText nomeBox = new EditText(context);
                nomeBox.setHint("Nome:");
                layout.addView(nomeBox); // Notice this is an add method

// Add another TextView here for the "Description" label
                final EditText idadeBox = new EditText(context);
                idadeBox.setHint("Idade");
                layout.addView(idadeBox); // Another add method

                AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                        .setTitle("Inserir amigo:")
                        .setView(layout)
                        .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference referencetoPlayers = database.getReference().child("Players");
                                Player player = new Player();
                                player.setNome(nomeBox.getText().toString());
                                player.setIdade(Integer.parseInt(idadeBox.getText().toString()));
                                referencetoPlayers.child(player.getId()).setValue(player);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // TODO: 5/4/17 Delete Task
                            }
                        })
                        .create();
                dialog.show();
            }
        });
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