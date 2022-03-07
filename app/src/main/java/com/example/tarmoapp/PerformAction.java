package com.example.tarmoapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PerformAction extends AppCompatActivity {
    EditText text;
    static String hobby;
    EditText minutes;
    static int mins;
    Button saveBtn;
    boolean isTrueText;
    boolean isTrueMins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_action);

        text = (EditText)findViewById(R.id.TextHobby);
        minutes = (EditText)findViewById(R.id.minuteBox);
        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setEnabled(false);
        isTrueText = false;
        isTrueMins = false;

        text.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                trueChecker();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                isTrueText = true;

                trueChecker();
            }
        });

        minutes.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                trueChecker();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                isTrueMins = true;

                trueChecker();
            }
        });
    }

    public void onPerformHistoryClick(View view) {
        Intent nextActivity = new Intent(PerformAction.this, CalendarActivity.class);
        startActivity(nextActivity);
    }

    public void onSaveClick(View view){
        hobby = text.getText().toString();
        mins = Integer.parseInt(String.valueOf(minutes.getText()));

        text.setText("");
        minutes.setText("");

        isTrueText = false;
        isTrueMins = false;

        trueChecker();

        Log.i("hobby", "onSaveClick: " + hobby);
        Log.i("mins", "onSaveClick: " + mins);
    }

    public void trueChecker(){
        if(isTrueText == true && isTrueMins == true){
            saveBtn.setEnabled(true);
        }else{
            saveBtn.setEnabled(false);
        }
    }

    public static void setHobby(String newString){
        hobby = newString;
    }

    public static void setMins(int newMins){
        mins = newMins;
    }

    public static String getHobby(){
        return hobby;
    }

    public static int getMins(){
        return mins;
    }
}