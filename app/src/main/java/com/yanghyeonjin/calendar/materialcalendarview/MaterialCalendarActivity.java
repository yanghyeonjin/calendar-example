package com.yanghyeonjin.calendar.materialcalendarview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.yanghyeonjin.calendar.MainActivity;
import com.yanghyeonjin.calendar.MultipleEventDecorator;
import com.yanghyeonjin.calendar.OneEventDecorator;
import com.yanghyeonjin.calendar.R;

import java.util.ArrayList;

public class MaterialCalendarActivity extends AppCompatActivity {

    private MaterialCalendarView calendarView;

    private static final String TAG = "MainActivity";
    private Context context;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_calendar);

        context = MaterialCalendarActivity.this;
        activity = MaterialCalendarActivity.this;


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


        // add multiple event dots per day
        CalendarDay day1 = CalendarDay.from(2020, 7,1);
        CalendarDay day2 = CalendarDay.from(2020, 7,2);
        ArrayList<CalendarDay> days = new ArrayList<>();
        ArrayList<CalendarDay> days2 = new ArrayList<>();
        days.add(day1);
        days2.add(day2);
        int[] threeColors = {Color.rgb(0, 0, 255), Color.rgb(0, 255, 0), Color.rgb(255, 0, 0)};
        int[] twoColors = {Color.rgb(3, 100, 100), Color.rgb(5, 200, 0)};
        calendarView.addDecorators(new MultipleEventDecorator(threeColors, days));
        calendarView.addDecorators(new MultipleEventDecorator(twoColors, days2));


        // add one dot
        ArrayList<CalendarDay> days5 = new ArrayList<>();
        CalendarDay day5 = CalendarDay.from(2020, 7,5);
        CalendarDay day6 = CalendarDay.from(2020, 7,6);
        CalendarDay day7 = CalendarDay.from(2020, 7,7);
        CalendarDay day8 = CalendarDay.from(2020, 7,8);
        days5.add(day5);
        days5.add(day6);
        days5.add(day7);
        days5.add(day8);
        calendarView.addDecorators(new OneEventDecorator(ContextCompat.getColor(context, R.color.selectionColor), days5));
    }
}