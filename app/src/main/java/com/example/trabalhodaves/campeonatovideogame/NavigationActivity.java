package com.example.trabalhodaves.campeonatovideogame;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.example.trabalhodaves.campeonatovideogame.model.Player;
import com.example.trabalhodaves.campeonatovideogame.model.Time;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
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
                        final EditText edtNome = new EditText(NavigationActivity.this);
                        final EditText edtIdade = new EditText(NavigationActivity.this);
                        @Override
                        public void onClick(View view) {
                            AlertDialog dialog = new AlertDialog.Builder(NavigationActivity.this)
                                    .setTitle("Inserir time:")
                                    .setView(edtNome)
                                    .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            DatabaseReference referencetoPlayers = database.getReference().child("Times");
                                            Time time = new Time();
                                            time.setNome(edtNome.getText().toString());
                                            referencetoPlayers.child(time.getId()).setValue(time);
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
                            final EditText edtNome = new EditText(NavigationActivity.this);
                            final EditText edtIdade = new EditText(NavigationActivity.this);
                            @Override
                            public void onClick(View view) {
                                AlertDialog dialog = new AlertDialog.Builder(NavigationActivity.this)
                                        .setTitle("Inserir amigo:")
                                        .setView(edtNome)
                                        .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                                DatabaseReference referencetoPlayers = database.getReference().child("Players");
                                                Player player = new Player();
                                                player.setNome(edtNome.getText().toString());
                                                player.setIdade(0);
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
}
