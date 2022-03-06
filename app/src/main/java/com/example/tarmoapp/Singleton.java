package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Singleton extends AppCompatActivity {

    private static final Singleton instance = new Singleton();
    public static List<FitnessTip> list = Arrays.asList(
            new FitnessTip("", ""),
            new FitnessTip("1. terveysvinkki", "Juo sukat ja vaihda vedet"),
            new FitnessTip("2. terveysvinkki", "Vaihda vedet ja juo sukat"));

    public static Singleton getInstance() {
        return instance;
    }

    public List<FitnessTip> getPresidents() {
        return list;
    }

    private Singleton() {
        list = new ArrayList<>();
    }
}
