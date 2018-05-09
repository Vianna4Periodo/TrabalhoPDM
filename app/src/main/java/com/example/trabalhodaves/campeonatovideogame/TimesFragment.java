package com.example.trabalhodaves.campeonatovideogame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.trabalhodaves.campeonatovideogame.model.Time;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TimesFragment extends Fragment {
    private FrameLayout fragmentContainer;
    ListView listTimes;
    FirebaseDatabase database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_times, container, false);

        listTimes = view.findViewById(R.id.listTimes);
        fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_times);
        database = FirebaseDatabase.getInstance();

        DatabaseReference referenceTimes = database.getReference().child("Times");
        referenceTimes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                preencherListView(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        return view;
    }

    private void preencherListView(DataSnapshot dataSnapshot) {
        ArrayList<Time> players = new ArrayList<>();
        for (DataSnapshot ds: dataSnapshot.getChildren()) {
            Time m = ds.getValue(Time.class);

            players.add(m);
        }
        ArrayAdapter<Time> adapter = new ArrayAdapter<>(fragmentContainer.getContext(), android.R.layout.simple_list_item_1, players);
        listTimes.setAdapter(adapter);

        listTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Time timeSelecionado = (Time) adapterView.getItemAtPosition(i);

                NavigationActivity activity = (NavigationActivity) getActivity();
                activity.changeFragment(timeSelecionado);

            }
        });
    }


//13160 ano
    public TimesFragment(){

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
