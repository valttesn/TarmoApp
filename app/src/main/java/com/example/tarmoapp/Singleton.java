package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Singleton extends AppCompatActivity {

    private static final Singleton instance = new Singleton();
    public static List<FitnessTip> list = Arrays.asList(
            new FitnessTip("1. terveysvinkki", "Vaihda vedet ja juo sukat"),
            new FitnessTip("2. terveysvinkki","Juo sukat ja vaihda vedet"),
            new FitnessTip("Paasikivi, Juho Kusti",  "Äkäinen ukko"),
            new FitnessTip("Kekkonen, Urho Kaleva",  "Pelimies"),
            new FitnessTip("Koivisto, Mauno Henrik",  "Manu"),
            new FitnessTip("Ahtisaari, Martti Oiva", "Mahtisaari"),
            new FitnessTip("Halonen, Tarja Kaarina",  "Eka naispressa"),
            new FitnessTip("Niinistö, Sauli Väinämö", "Owner of Lennu, the First Dog"));

    public static Singleton getInstance() {
        return instance;
    }

    public List<FitnessTip> getFitnessTips() {
        return list;
    }

    private Singleton() {
        list = new ArrayList<>();
    }
}
