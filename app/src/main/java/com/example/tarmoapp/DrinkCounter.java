package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;

public class DrinkCounter extends AppCompatActivity {

    private static int value;
    private static int step = 2;

    public DrinkCounter(){

    }

    //Metodi asettaa muuttujan arvoksi arvon SharedPreferenceistä
    public static void setValue(int tempValue) {
        value = tempValue;
    }
    //Metodi asettaa yhden "stepin" arvoksi vesiannosta lisätessä arvon SharedPreferenceistä
    public static void setStep(int tempStep) {
        step = tempStep;
    }

    public static void Raise(){
        value += step;
    }

    public static int Value(){
        return value;
    }

    public static int Step() {
        return step;
    }

    public static void changeStep(int newWaterValue) {
        step = newWaterValue;
    }
}
