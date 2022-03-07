package com.example.tarmoapp;

public class FitnessTip {
    private String name, comment;

    public FitnessTip(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    //Palauttaa fitness tipsien nimen
    public String getName() {
        return name;
    }

    //Palauttaa fitness tipsien tekstin
    public String getComment() {
        return comment;
    }

    // Palauttaa ListView listaa varten pelkästään listasta nimen
    @Override
    public String toString() {
        return name;
    }
}