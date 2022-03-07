package com.example.tarmoapp;

import android.util.Log;

// Luokka josta fitness tipsit luodaan listaan

public class FitnessTip {
    private String name, comment;

    public FitnessTip(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String  getComment() {
        return comment;
    }

    @Override
    public String toString() {
        Log.i("DMG", "Fitness tip" + name + comment);
        return name;
    }
}