package com.example.tarmoapp;

import android.util.Log;

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
        Log.i("DMG", "President: " + name + comment);
        return name;
    }
}