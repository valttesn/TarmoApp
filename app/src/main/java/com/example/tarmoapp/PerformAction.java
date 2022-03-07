package com.example.tarmoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PerformAction extends AppCompatActivity {
    EditText text, minutes;
    private static String hobby;
    private static int mins;
    Button saveBtn;
    boolean isTrueText, isTrueMins;

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


        // Listenerit molempiin EditTexteihin, jotta Tallenna-nappi pysyy lukittuna kunnes käyttäjä on täyttänyt vaadittavat tiedot
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

    //Metodi avaa CalendarActivityn

    public void onPerformHistoryClick(View view) {
        Intent nextActivity = new Intent(PerformAction.this, CalendarActivity.class);
        startActivity(nextActivity);
    }

    //Metodi Tallenna-napille. Tallentamisen lisäksi nappi tyhjentää molemmat tekstikentät ja lukitsee napin.

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
        saveData();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("NUMBER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Urheilulaji", PerformAction.getHobby());
        editor.putInt("Urheiltu aika", PerformAction.getMins());
        editor.apply();
    }

    // Toiminnallisuus napin lukitsemiselle

    public void trueChecker(){
        if(isTrueText == true && isTrueMins == true){
            saveBtn.setEnabled(true);
        }else{
            saveBtn.setEnabled(false);
        }
    }

    public static String getHobby(){
        return hobby;
    }

    public static int getMins(){
        return mins;
    }

    public static void setHobby(String tempValue) {
        hobby = tempValue;
    }
    public static void setMins(int tempStep) {
        mins = tempStep;
    }

    public static int Mins(){
        return mins;
    }

    public static String Hobby() {
        return hobby;
    }
}