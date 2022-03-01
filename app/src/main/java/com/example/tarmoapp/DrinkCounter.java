package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;

public class DrinkCounter extends AppCompatActivity {

    private static int value = 0;
    private static int step = 2;

    public DrinkCounter(){
    }

    public static void Raise(){
        value += step;
    }

    public static int Value(){
        return value;
    }
}
