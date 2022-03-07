package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class PerformHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_history);
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("HOBBY","Value");
    }
}