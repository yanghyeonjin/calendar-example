package com.yanghyeonjin.calendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private MaterialCalendarView calendarView;

    private static final String TAG = "MainActivity";
    private Context context;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        activity = MainActivity.this;


        // 아이디 연결
        calendarView = findViewById(R.id.calendarView);


        // 타이틀 포맷
        TitleFormatter titleFormatter = new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                day.getYear();
                day.getMonth();
                return day.getYear() + "년 " + day.getMonth() + "월";
            }
        };
        calendarView.setTitleFormatter(titleFormatter);


        // dot event
        CalendarDay day1 = CalendarDay.from(2020, 7,1);
        CalendarDay day2 = CalendarDay.from(2020, 7,2);
        CalendarDay day3 = CalendarDay.from(2020, 7,3);
        CalendarDay day4 = CalendarDay.from(2020, 7,4);
        ArrayList<CalendarDay> days = new ArrayList<>();
        days.add(day1);
        days.add(day2);
        days.add(day3);
        days.add(day4);
        calendarView.addDecorators(new EventDecorator(R.color.colorAccent, days));
    }
}