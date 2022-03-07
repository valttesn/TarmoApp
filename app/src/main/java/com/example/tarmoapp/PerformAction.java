package com.example.tarmoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PerformAction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_action);
        Intent intent = getIntent();
    }

    public void onPerformHistoryClick(View view) {
        Intent nextActivity = new Intent(PerformAction.this, PerformHistory.class);
        Bundle bundle = new Bundle();
        bundle.putString("HOBBY","Value");
        nextActivity.putExtras(bundle);
        startActivity(nextActivity);
    }

    public void onSaveClick(View view){
        EditText text = (EditText)findViewById(R.id.TextHobby);
        String value = text.getText().toString();
        EditText number = (EditText)findViewById(R.id.TextNumber);
        String min = number.getText().toString();
    }
}