package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;

public class DrinkCounter extends AppCompatActivity {

    private static int value;
    private static int step = 2;

    public DrinkCounter(){

    }

    public static void setValue(int tempValue) {
        value = tempValue;
    }
    public static void setStep(int tempStep) {
        step = tempStep;
    }

    public static void Raise(){
        value += step;
    }

    public static int Value(){
        return value;
    }

    public static void setNewValue(int newValue){
        value = newValue;
    }

    public static void changeStep(int newWaterValue) {
        step = newWaterValue;
    }
    public static int Step() {
        return step;
    }
}
