package com.example.tarmoapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PerformAction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_action);

        int index = getIntent().getIntExtra("presidentIndex", 0);
    }

    public PerformAction(){

    }
}
