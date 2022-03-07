package com.example.tarmoapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private TextView drinkView;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drinkView = findViewById(R.id.drinksView);
        Log.d("DMG", "onCreate() called");
        loadData();
        updateData();

        TextView mood = findViewById(R.id.moodView);
        Log.d("Unet", "Nukuit" + Sleep.getSleepAmount());

        if(callSleep() <= 7){
            mood.setText("Tarmoa väsyttää");
        }else{
            mood.setText("Tarmo on virkeä!");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        drinkView = findViewById(R.id.drinksView);
        DrinkCounter.Raise();
        drinkView.setText("Olet juonut " + Integer.toString((DrinkCounter.Value())) + " desiä vettä tänään.");
    }

    public void onPerformActionClick(View view) {
        Intent nextActivity = new Intent(MainActivity.this, PerformAction.class);
        startActivity(nextActivity);
    }

    public void onClickSleep(View view) {
        Intent nextActivity = new Intent(MainActivity.this, Sleep.class);
        startActivity(nextActivity);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id)
        {
            case R.id.nav_calendar:
                onCalendarClick();
                break;

            case R.id.nav_settings:
                onSettingsClick();
                break;

            case R.id.nav_fitnesstips:
                onFitnessTipsClick();
                break;
        }
        return true;
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("NUMBER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Juodut vedet", DrinkCounter.Value());
        editor.putInt("Urheilu", PerformAction.getMins());
        editor.putInt("Uni", Sleep.getSleepAmount());
        editor.putString("Urheilu", PerformAction.getHobby());

        editor.apply();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("NUMBER", MODE_PRIVATE);
        DrinkCounter.setValue(sharedPreferences.getInt("Juodut vedet", DrinkCounter.Value()));
        DrinkCounter.setStep(sharedPreferences.getInt("Veden määrä", DrinkCounter.Step()));
        Log.i("DMG", String.valueOf(DrinkCounter.Step()));
    }

    public void updateData() {
        drinkView.setText("Olet juonut " + Integer.toString(DrinkCounter.Value()) + " desiä vettä tänään.");
    }

    public void onPause() {
        super.onPause();
        Log.i("DMG", String.valueOf(DrinkCounter.Step()));
        Log.i("DMG", "onPause() called");
        saveData();
    }

    public void onCalendarClick() {
        Intent calendarActivity = new Intent(MainActivity.this, CalendarActivity.class);
        startActivity(calendarActivity);
    }

    public void onSettingsClick() {
        Intent settingsActivity = new Intent(MainActivity.this, settingsActivity.class);
        startActivity(settingsActivity);
    }

    public void onFitnessTipsClick() {
        Intent fitnessTipsActivity = new Intent(MainActivity.this, FitnessTipsActivity.class);
        startActivity(fitnessTipsActivity);
    }

    public static double callSleep(){
        return Sleep.getSleepAmount();
    }
}