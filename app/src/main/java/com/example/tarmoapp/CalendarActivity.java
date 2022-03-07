package com.example.tarmoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {
    private CalendarView calendar;
    private TextView sleepAmount, waterDrankAmount, currentlyselectedDate, exerciseAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        waterDrankAmount = findViewById(R.id.textViewDailyWaterAmount);
        sleepAmount = findViewById(R.id.textViewDailySleepAmount);
        calendar = findViewById(R.id.userCalendar);
        currentlyselectedDate = findViewById(R.id.textViewCalendarSelectedDate);
        exerciseAmount = findViewById(R.id.textViewDailyExercise);

        refreshSleepAmount();
        refreshWaterDrankAmount();
        refreshExerciseAmount();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = i2 + "/" + i1 + "/" +i;
                currentlyselectedDate.setText("Valittu päivämäärä on "+ date);

            }
        });
    }

    public void onClickSetDay(View view){
        refreshSleepAmount();
        refreshWaterDrankAmount();
        refreshExerciseAmount();
    }

    public void refreshSleepAmount() {
        sleepAmount.setText("Nukuit " + Integer.toString(Sleep.SleepAmount()) + " h");
    }

    public void refreshWaterDrankAmount() {
        waterDrankAmount.setText("Joit " + Integer.toString(DrinkCounter.Value()) + " dl vettä");
    }

    public void refreshExerciseAmount() {
        exerciseAmount.setText("Harrastit " + (PerformAction.getHobby()) + " " + (PerformAction.getMins() + " minuuttia."));
    }
}