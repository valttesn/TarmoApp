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
    TextView drinkView;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public int waterDrank;

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

    public void onItemClick(View v) {
        Intent nextActivity = new Intent(MainActivity.this, Excersise.class);
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

        editor.apply();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("NUMBER", MODE_PRIVATE);
        int waterTemp = DrinkCounter.Value();
        Log.d("DMG", String.valueOf(sharedPreferences.getInt("Juodut vedet", waterTemp)));
        waterDrank = sharedPreferences.getInt("Juodut vedet", waterTemp);
        DrinkCounter.setValue(waterDrank);
        Log.i("DMG", String.valueOf(waterTemp));
    }

    public void updateData() {
        drinkView.setText(Integer.toString(waterDrank));
    }

    public void onPause() {
        super.onPause();
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
}