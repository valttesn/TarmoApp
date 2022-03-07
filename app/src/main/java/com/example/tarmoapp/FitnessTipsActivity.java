package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FitnessTipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_tips);

        ListView lv = findViewById(R.id.list);

        //Luodaan listview fitness tipseille ja poimitaan nykyiset arvot listasta
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Singleton.getInstance().getFitnessTips()));
        //Lisätään klikkauksen kuuntelu ja vaihdetaan aktiviteettia mikäli jotakin kohtaa listasta painetaan
        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent nextActivity = new Intent(FitnessTipsActivity.this, DisplayFitnessTipActivity.class);
            nextActivity.putExtra("fitnessIndex", i);
            startActivity(nextActivity);
        });
    }
}