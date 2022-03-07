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

    public static void Raise(){
        value += step;
    }

    public static int Value(){
        return value;
    }
}

/*
package com.example.tarmoapp;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class DrinkCounter extends AppCompatActivity {

    private static int value;
    private static String step;
    private static int stepFinal;

    public DrinkCounter(){
        EditText wInput = (EditText) findViewById(R.id.waterInput);
        this.step = wInput.getText().toString();
        this.stepFinal = Integer.parseInt(step);
    }

    public static void Raise(){
        value += stepFinal;
    }

    public static int Value(){
        return value;
    }
}
 */
