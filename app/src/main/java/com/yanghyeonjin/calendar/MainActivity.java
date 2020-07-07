package com.yanghyeonjin.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

public class MainActivity extends AppCompatActivity {
    private MaterialCalendarView calendarView;

    private static final String TAG = "MainActivity";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;


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
    }
}