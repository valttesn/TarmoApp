package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
public class settingsActivity extends AppCompatActivity {
    private static boolean dark = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button btn = (Button) findViewById(R.id.darkBtn);

        if(dark == true){
            btn.setText("Tumma");
        }else{
            btn.setText("Vaalea");
        }
    }

    public void onClickDark(View view){
        if(dark == false) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
            dark = true;
        }else if(dark == true){
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
            dark = false;
        }
    }
}
