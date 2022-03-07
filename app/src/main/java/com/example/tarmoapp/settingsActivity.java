package com.example.tarmoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class settingsActivity extends AppCompatActivity {
    private static boolean dark = false;
    private EditText editWaterValue;
    private TextView currentWaterValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editWaterValue = findViewById(R.id.waterInput);
        currentWaterValue = findViewById(R.id.textViewCurrentWaterAmount);

        Button btn = (Button) findViewById(R.id.darkBtn);

        if (dark == true) {
            btn.setText("Tumma");
        } else {
            btn.setText("Vaalea");
        }

        currentWaterValue();
    }

    public void onClickDark(View view){
        if(dark == false) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
            dark = true;
        }else if(dark == true){
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
            dark = false;
        }
    }

    public void destroyData(View view) {
        super.onDestroy();
        clearApplicationData();
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                }
            }
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            int i = 0;
            while (i < children.length) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
                i++;
            }
        }

        assert dir != null;
        return dir.delete();
    }

    public static boolean theme () {
        return dark;
    }

    public void onCLickSaveNewWaterValue(View view) {
        int tempValue = Integer.parseInt(String.valueOf(editWaterValue.getText()));
        Log.i("DMG", String.valueOf(tempValue));
        DrinkCounter.changeStep(tempValue);
        currentWaterValue();
        saveData();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("NUMBER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Veden määrä", DrinkCounter.Step());

        editor.apply();
    }

    public void currentWaterValue() {
        currentWaterValue.setText("Nykyinen vesiannos on: "+ Integer.toString(DrinkCounter.Step()) + " dl.");
    }
}
