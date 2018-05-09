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

import com.example.trabalhodaves.campeonatovideogame.model.Player;
import com.example.trabalhodaves.campeonatovideogame.model.Time;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayersFragment extends Fragment {
    private FrameLayout fragmentContainer;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_players, container, false);

        fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_players);
        listView = view.findViewById(R.id.listViewPlayers);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referenceTimes = database.getReference().child("Times");
        referenceTimes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                preencherListView(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void preencherListView(DataSnapshot dataSnapshot) {
        ArrayList<Player> players = new ArrayList<>();
        for (DataSnapshot ds: dataSnapshot.getChildren()) {
            Time m = ds.getValue(Time.class);

            players.add(m.getPlayer());
        }
        ArrayAdapter<Player> adapter = new ArrayAdapter<>(fragmentContainer.getContext(), android.R.layout.simple_list_item_1, players);
        listView.setAdapter(adapter);

    }

    public PlayersFragment(){

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
