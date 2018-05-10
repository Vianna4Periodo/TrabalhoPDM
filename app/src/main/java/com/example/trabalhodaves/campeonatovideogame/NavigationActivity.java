package com.example.trabalhodaves.campeonatovideogame;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.example.trabalhodaves.campeonatovideogame.model.Player;
import com.example.trabalhodaves.campeonatovideogame.model.Time;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity {

    private android.support.v4.app.Fragment currentFragment;
    private PlayersFragment playersFrag = new PlayersFragment();
    private TimesFragment timesFrag = new TimesFragment();
    private JogosFragment jogosFrag = new JogosFragment();

    private BottomViewPagerAdapter bottomViewPagerAdapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    private AHBottomNavigationViewPager viewPagerBottom;
    private AHBottomNavigation bottomNavigation;
    private FloatingActionButton floatingActionButton;

    private ArrayList<Time> times = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater  = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sorteio_jogos:
                sortearJogos();
        }
        return true;
    }



    private void sortearJogos() {
        if(times.size()<2){
            Toast.makeText(this.getApplicationContext(),"Não há times suficientes",
                    Toast.LENGTH_LONG).show();
            return;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referenceTimes = database.getReference().child("Times");
        referenceTimes.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Time m = ds.getValue(Time.class);
                    times.add(m);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        initUI();
    }

    private void initUI() {
        bottomNavigation = findViewById(R.id.bottom_navigation);
        viewPagerBottom = findViewById(R.id.view_pager_bottom);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);

        viewPagerBottom.setOffscreenPageLimit(2);
        bottomViewPagerAdapter = new BottomViewPagerAdapter(getSupportFragmentManager());
        bottomViewPagerAdapter.add(jogosFrag);
        bottomViewPagerAdapter.add(playersFrag);
        bottomViewPagerAdapter.add(timesFrag);
        viewPagerBottom.setAdapter(bottomViewPagerAdapter);

        currentFragment = bottomViewPagerAdapter.getCurrentFragment();

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1,
                R.drawable.ic_dashboard_black_24dp, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2,
                R.drawable.ic_home_black_24dp, R.color.color_tab_1);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3,
                R.drawable.ic_notifications_black_24dp, R.color.color_tab_1);

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);

        bottomNavigation.addItems(bottomNavigationItems);


        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (currentFragment == null) {
                    currentFragment = bottomViewPagerAdapter.getCurrentFragment();
                }

                if (currentFragment != null) {
                    if (currentFragment instanceof JogosFragment) {
                        jogosFrag.willBeHidden();
                    } else if (currentFragment instanceof TimesFragment) {
                        timesFrag.willBeHidden();
                    } else if (currentFragment instanceof PlayersFragment) {
                        playersFrag.willBeHidden();
                    }
                }

                //Aqui é onde é setado qual o fragment atual
                //Em seguida é pego o fragment atual e feito o fade dependendo de qual instancia for
                viewPagerBottom.setCurrentItem(position, false);
                currentFragment = bottomViewPagerAdapter.getCurrentFragment();

                if (currentFragment instanceof JogosFragment) {
                    jogosFrag.willBeDisplayed();
                } else if (currentFragment instanceof TimesFragment) {
                    timesFrag.willBeDisplayed();
                } else if (currentFragment instanceof PlayersFragment) {
                    playersFrag.willBeDisplayed();
                }

                if(position == 2){
                    //isto é executado se o fab for clicado na tela de times
                    bottomNavigation.setNotification("", 1);

                    floatingActionButton.setVisibility(View.VISIBLE);
                    floatingActionButton.setAlpha(0f);
                    floatingActionButton.setScaleX(0f);
                    floatingActionButton.setScaleY(0f);
                    floatingActionButton.animate()
                            .alpha(1)
                            .scaleX(1)
                            .scaleY(1)
                            .setDuration(300)
                            .setInterpolator(new OvershootInterpolator())
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    floatingActionButton.animate()
                                            .setInterpolator(new LinearOutSlowInInterpolator())
                                            .start();
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            })
                            .start();

                    floatingActionButton.setClickable(true);
                    floatingActionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Context context = view.getContext();
                            final LinearLayout layout = new LinearLayout(context);
                            layout.setOrientation(LinearLayout.VERTICAL);


                            final EditText nomeBox = new EditText(context);
                            nomeBox.setHint("Nome:");
                            layout.addView(nomeBox); // Notice this is an add method

                            final Spinner playerBox = new Spinner(context);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference referencePlayers = database.getReference().child("Players");


                            referencePlayers.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    ArrayList<Player> players = new ArrayList<>();
                                    for (DataSnapshot ds: dataSnapshot.getChildren()) {
                                        Player m = ds.getValue(Player.class);

                                        players.add(m);
                                    }
                                    ArrayAdapter<Player> adapter = new ArrayAdapter<>(layout.getContext(),
                                            android.R.layout.simple_list_item_1, players);
                                    playerBox.setAdapter(adapter);


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                            layout.addView(playerBox); // Another add method

                            AlertDialog dialog = new AlertDialog.Builder(NavigationActivity.this)
                                    .setTitle("Inserir time:")
                                    .setView(layout)
                                    .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Player player = (Player) playerBox.getSelectedItem();
                                            if(player == null){
                                                Toast.makeText(getApplicationContext(),"Não é " +
                                                        "possível criar um time sem um amigo vinculado!",
                                                        Toast.LENGTH_LONG)
                                                        .show();
                                            }else {
                                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                                DatabaseReference referenceTimes = database.getReference().child("Times");
                                                DatabaseReference referencePlayers = database.getReference().child("Players");
                                                Time time = new Time();

                                                time.setNome(nomeBox.getText().toString());
                                                time.setPlayer(player);
                                                referenceTimes.child(time.getId()).setValue(time);
                                                referencePlayers.child(player.getId()).removeValue();
                                            }
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
                }else
                if (position == 1) {

                    //isto é executado se o fab for clicado na tela de amigos
                    bottomNavigation.setNotification("", 1);

                    floatingActionButton.setVisibility(View.VISIBLE);
                    floatingActionButton.setAlpha(0f);
                    floatingActionButton.setScaleX(0f);
                    floatingActionButton.setScaleY(0f);
                    floatingActionButton.animate()
                            .alpha(1)
                            .scaleX(1)
                            .scaleY(1)
                            .setDuration(300)
                            .setInterpolator(new OvershootInterpolator())
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    floatingActionButton.animate()
                                            .setInterpolator(new LinearOutSlowInInterpolator())
                                            .start();
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            })
                            .start();

                        floatingActionButton.setClickable(true);
                        floatingActionButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Context context = view.getContext();
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

                                AlertDialog dialog = new AlertDialog.Builder(NavigationActivity.this)
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

                } else {
                    if (floatingActionButton.getVisibility() == View.VISIBLE) {
                        floatingActionButton.animate()
                                .alpha(0)
                                .scaleX(0)
                                .scaleY(0)
                                .setDuration(300)
                                .setInterpolator(new LinearOutSlowInInterpolator())
                                .setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        floatingActionButton.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {
                                        floatingActionButton.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                })
                                .start();
                    }
                }
                return true;
            }
        });

        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                Log.d("DemoActivity", "BottomNavigation Position: " + y);
            }
        });
    }

    public void changeFragment(Time timeSelecionado) {
        android.support.v4.app.FragmentManager manager =  getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction tx = manager.beginTransaction();

        TimesDetalheFragment detalheFragment = new TimesDetalheFragment();
        Bundle parametros = new Bundle();
        parametros.putSerializable("time", timeSelecionado);
        detalheFragment.setArguments(parametros);

        tx.replace(currentFragment.getId(), detalheFragment);
        tx.commit();
    }
}
