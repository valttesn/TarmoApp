package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

public class Sleep extends AppCompatActivity {
    public int hours;
    public int minutes;

    TimePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        picker = findViewById(R.id.timePicker);
    }

    public void onClickSave(View view){
        hours = picker.getHour();
        minutes = picker.getMinute();

        Intent nextActivity = new Intent(Sleep.this, MainActivity.class);
        startActivity(nextActivity);
    }
}