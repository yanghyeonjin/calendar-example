package com.yanghyeonjin.calendar.weekcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.yanghyeonjin.calendar.R;

import org.threeten.bp.LocalDate;

public class WeekCalendarActivity extends AppCompatActivity {

    private MaterialCalendarView weekCalendar;
    private TextView tvCurrentMonth;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_calendar);

        context = WeekCalendarActivity.this;


        // 아이디 연결
        weekCalendar = findViewById(R.id.weekCalendar);
        tvCurrentMonth = findViewById(R.id.tvCurrentMonth);


        // calendar 셋팅
        // custom top bar 사용하기 위해 hide 처리
        weekCalendar.setTopbarVisible(false);
        CalendarDay currentDate = weekCalendar.getCurrentDate();
        tvCurrentMonth.setText(currentDate.getYear() + "년 " + currentDate.getMonth() + "월");

        // 처음 들어왔을 때 오늘 날짜에 선택되어 있도록
        weekCalendar.setSelectedDate(LocalDate.now());

        // 주말 highlight
        weekCalendar.addDecorators(new SaturdayDecorator(), new SundayDecorator(context));

        // week change 리스너
        weekCalendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                weekCalendar.setSelectedDate(date.getDate());
                tvCurrentMonth.setText(date.getYear() + "년 " + date.getMonth() + "월");
            }
        });
    }
}