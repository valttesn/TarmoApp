package com.example.tarmoapp;

public class drinkCounter {

    private static int value = 0;
    private static int step = 2;

    public drinkCounter(){
    }

    public static void Raise(){
        value += step;
    }

    public static int Value(){
        return value;
    }
}
