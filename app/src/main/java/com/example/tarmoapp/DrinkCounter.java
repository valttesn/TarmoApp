package com.example.tarmoapp;

public class DrinkCounter {

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
