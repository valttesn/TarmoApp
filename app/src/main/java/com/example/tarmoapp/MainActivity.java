package com.example.tarmoapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, SensorEventListener {
    TextView drinkView;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private TextView stepsCounter;
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    private boolean isCounterSensorPresent;
    int stepCount = 0;

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

        stepsCounter = findViewById(R.id.stepsView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        }else{
            stepsCounter.setText("Laskurin sensori ei ole käytössä");
            isCounterSensorPresent = false;
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
        DrinkCounter.setValue(sharedPreferences.getInt("Juodut vedet", DrinkCounter.Value()));
    }

    public void updateData() {
        drinkView.setText("Olet juonut " + Integer.toString(DrinkCounter.Value()) + " desiä vettä tänään.");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.i("DMG", "onPause() called");
        saveData();

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            sensorManager.unregisterListener(this,mStepCounter);
        }
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

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor == mStepCounter){
            stepCount = (int) sensorEvent.values[0];
            stepsCounter.setText(String.valueOf(stepCount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            sensorManager.registerListener(this,mStepCounter,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}