package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

public class Sleep extends AppCompatActivity {
    public static int hours, minutes, sleepAmount;

    private TimePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        picker = findViewById(R.id.timePicker);
        picker.setIs24HourView(true);
    }

    public void onClickSave(View view){
        hours = picker.getHour();
        minutes = picker.getMinute() / 60;

        Log.i("arvot", hours + "+" + minutes);

        sleepAmount = hours + minutes;

        Log.i("nuku", "onClickSave: " + sleepAmount);
        Intent nextActivity = new Intent(Sleep.this, MainActivity.class);
        startActivity(nextActivity);
    }

    public static void setSleep(int newAmount){
        sleepAmount = newAmount;
    }

    public static int getSleepAmount(){
        return sleepAmount;
    }
}