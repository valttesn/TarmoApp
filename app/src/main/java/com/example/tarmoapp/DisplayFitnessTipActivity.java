package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayFitnessTipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_fitness_tip);

        int index = getIntent().getIntExtra("presidentIndex", 0);
        FitnessTip fitnessTip = Singleton.getInstance().list.get(index);

        ((TextView) findViewById(R.id.textViewNumberOfTip)).setText(fitnessTip.getName());
        ((TextView) findViewById(R.id.textViewFitnessTip)).setText(fitnessTip.getComment());
    }
}