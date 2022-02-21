package com.example.tarmoapp;

public class drinkCounter {

    private static double value = 0;
    private static double step = 0.1;

    public drinkCounter(){
    }

    public static void Raise(){
        value += step;
    }

    public static double Value(){
        return value;
    }
}
