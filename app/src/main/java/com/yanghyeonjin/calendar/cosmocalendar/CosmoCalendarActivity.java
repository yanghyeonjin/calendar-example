package com.yanghyeonjin.calendar.cosmocalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.yanghyeonjin.calendar.R;

public class CosmoCalendarActivity extends AppCompatActivity {

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmo_calendar);

        // 아이디 연결
        calendarView = findViewById(R.id.calendar_cosmo);
    }
}