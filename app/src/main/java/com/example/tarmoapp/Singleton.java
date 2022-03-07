package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Luokka josta fitness tipsit lisätään listaan
public class Singleton extends AppCompatActivity {

    private static final Singleton instance = new Singleton();
    public static List<FitnessTip> list = Arrays.asList(
            //Jostain syystä ensimmäinen rivi ei toimi listassa, mutta näyttää kaikki sen jälkeiset ihan normaalisti
            new FitnessTip("", ""),
            new FitnessTip("1. terveysvinkki", "Juo sukat ja vaihda vedet"),
            new FitnessTip("2. terveysvinkki", "Vaihda vedet ja juo sukat"));

    public static Singleton getInstance() {
        return instance;
    }

    //Palauttaa ArrayListin
    public List<FitnessTip> getFitnessTips() {
        return list;
    }

    private Singleton() {
        list = new ArrayList<>();
    }
}
