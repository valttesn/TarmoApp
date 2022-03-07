package com.example.tarmoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {
    private CalendarView calendar;
    private TextView sleepAmount, waterDrankAmount, currentlyselectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        waterDrankAmount = findViewById(R.id.textViewDailyWaterAmount);
        sleepAmount = findViewById(R.id.textViewDailySleepAmount);
        calendar = findViewById(R.id.userCalendar);
        currentlyselectedDate = findViewById(R.id.textViewCalendarSelectedDate);
        refreshSleepAmount();
        refreshWaterDrankAmount();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = i2 + "/" + i1 + "/" +i;
                currentlyselectedDate.setText("Valittu päivämäärä on "+ date);
            }
        });
    }

    public void refreshSleepAmount() {
        sleepAmount.setText("Nukuit " + Integer.toString(Sleep.getSleepAmount()) + " h");
    }

    public void refreshWaterDrankAmount() {
        waterDrankAmount.setText("Joit " + Integer.toString(DrinkCounter.Value()) + " dl vettä");
    }
}