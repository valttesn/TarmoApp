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
    private TextView drinkView, mood;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Drawer-menun luominen
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

        //Kerrotaan käyttäjälle onko hän saanut suositellun määrän unta
        mood = findViewById(R.id.moodView);
        Log.d("Unet", "Nukuit" + SleepActivity.SleepAmount());

        if(SleepActivity.SleepAmount() <= 7){
            mood.setText("Tarmoa väsyttää");
        }else{
            mood.setText("Tarmo on virkeä!");
        }
    }


    //Menu auki/kiinni
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //Vesiannoksen lisäysnappi
    public void onClick(View view){
        drinkView = findViewById(R.id.drinksView);
        DrinkCounter.Raise();
        drinkView.setText("Olet juonut " + Integer.toString((DrinkCounter.Value())) + " desiä vettä tänään.");
    }


    //Metodit liikuntasuoritusten ja unen määrälle
    public void onPerformActionClick(View view) {
        Intent nextActivity = new Intent(MainActivity.this, PerformActionActivity.class);
        startActivity(nextActivity);
    }

    public void onClickSleep(View view) {
        Intent nextActivity = new Intent(MainActivity.this, SleepActivity.class);
        startActivity(nextActivity);
    }


    //Menun navigaatio
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

    //Siirtyminen kalenteriin
    public void onCalendarClick() {
        Intent calendarActivity = new Intent(MainActivity.this, CalendarActivity.class);
        startActivity(calendarActivity);
    }

    //Siirtyminen asetuksiin
    public void onSettingsClick() {
        Intent settingsActivity = new Intent(MainActivity.this, settingsActivity.class);
        startActivity(settingsActivity);
    }

    //Siirtyminen fitnessvinkkeihin
    public void onFitnessTipsClick() {
        Intent fitnessTipsActivity = new Intent(MainActivity.this, FitnessTipsActivity.class);
        startActivity(fitnessTipsActivity);
    }

    //Metodi jolla tallennetaan juodun veden määrä sharedpreferensseihin
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("NUMBER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Juodut vedet", DrinkCounter.Value());

        editor.apply();
    }

    //Metodi jolla haetaan tallennetut arvot sharedpreferensseistä
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("NUMBER", MODE_PRIVATE);
        DrinkCounter.setValue(sharedPreferences.getInt("Juodut vedet", DrinkCounter.Value()));
        DrinkCounter.setStep(sharedPreferences.getInt("Veden määrä", DrinkCounter.Step()));
        PerformActionActivity.setHobby(sharedPreferences.getString("Urheilulaji", PerformActionActivity.Hobby()));
        PerformActionActivity.setMins(sharedPreferences.getInt("Urheiltu aika", PerformActionActivity.Mins()));
        SleepActivity.setSleep(sharedPreferences.getInt("Uni", SleepActivity.SleepAmount()));
    }

    //Päivitetään etusivun juodut vedet
    public void updateData() {
        drinkView.setText("Olet juonut " + Integer.toString(DrinkCounter.Value()) + " desiä vettä tänään.");
    }

    public void onPause() {
        super.onPause();
        saveData();
    }
}