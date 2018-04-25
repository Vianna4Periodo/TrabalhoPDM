package com.example.trabalhodaves.campeonatovideogame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab, fabAddPlayer, fabAddTime;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private Boolean isFabOpen = false;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        binding();
        setAnimations();

        fab.setOnClickListener(this);
        fabAddPlayer.setOnClickListener(this);
        fabAddTime.setOnClickListener(this);

    }

    private void setAnimations() {
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
    }

    private void binding() {
        fab = findViewById(R.id.fab);
        fabAddTime = findViewById(R.id.fabAddTimes);
        fabAddPlayer = findViewById(R.id.fabAddPlayer);
        list = findViewById(R.id.listviewAmigos);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:
                animateFab();
                break;
            case R.id.fabAddPlayer:
                Intent intent = new Intent(getApplicationContext(), AddPlayerActivity.class);
                startActivity(intent);
            break;
            case R.id.fabAddTimes:
                Intent intent1 = new Intent(getApplicationContext(), AddTimeActivity.class);
                startActivity(intent1);
                break;
        }
    }

    private void animateFab() {
        if(isFabOpen){
            fab.startAnimation(rotate_backward);
            fabAddPlayer.startAnimation(fab_close);
            fabAddTime.startAnimation(fab_close);
            fabAddPlayer.setClickable(false);
            fabAddTime.setClickable(false);
            isFabOpen = false;
        }else{
            fab.startAnimation(rotate_forward);
            fabAddPlayer.startAnimation(fab_open);
            fabAddTime.startAnimation(fab_open);
            fabAddPlayer.setClickable(true);
            fabAddTime.setClickable(true);
            isFabOpen = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
