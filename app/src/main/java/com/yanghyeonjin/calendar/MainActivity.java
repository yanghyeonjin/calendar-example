package com.yanghyeonjin.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class MainActivity extends AppCompatActivity {
    private MaterialCalendarView calendarView;

    private static final String TAG = "MainActivity";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;


        calendarView = findViewById(R.id.calendarView);
        Toast.makeText(context, String.valueOf(calendarView.getSelectedDates()), Toast.LENGTH_SHORT).show();
    }
}