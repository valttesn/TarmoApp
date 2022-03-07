package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

public class SleepActivity extends AppCompatActivity {
    public static int hours, minutes, sleepAmount;

    private TimePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        picker = findViewById(R.id.timePicker);
        picker.setIs24HourView(true);
    }

    //OnClick tallentaa kelloon valitut arvot sekä laskee tunnit + minuutit.

    public void onClickSave(View view){
        hours = picker.getHour();
        minutes = picker.getMinute() / 60;

        Log.i("arvot", hours + "+" + minutes);

        sleepAmount = hours + minutes;
        saveData();

        Log.i("nuku", "onClickSave: " + sleepAmount);
        Intent nextActivity = new Intent(SleepActivity.this, MainActivity.class);
        startActivity(nextActivity);
    }

    //Metodi jolla ylikirjoitetaan nukuttu aika
    public static void setSleep(int newAmount){
        sleepAmount = newAmount;
    }

    //Metodi joka palauttaa nukutun ajan
    public static int SleepAmount(){
        return sleepAmount;
    }

    //Metodi jolla tallennetaan nukuttu aika sharedpreferensseihin
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("NUMBER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Uni", SleepActivity.SleepAmount());
        editor.apply();
    }

}