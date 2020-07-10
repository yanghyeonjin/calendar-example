package com.yanghyeonjin.calendar.weekcalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.yanghyeonjin.calendar.R;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;

public class WeekCalendarActivity extends AppCompatActivity {

    private MaterialCalendarView weekCalendar;
    private TextView tvCurrentMonth;

    private Context context;

    private RecyclerView rvTodoList;
    private RecyclerView.Adapter todoAdapter;
    private RecyclerView.LayoutManager todoLayoutManager;
    private ArrayList<Todo> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_calendar);

        context = WeekCalendarActivity.this;


        // 아이디 연결
        weekCalendar = findViewById(R.id.weekCalendar);
        tvCurrentMonth = findViewById(R.id.tvCurrentMonth);
        rvTodoList = findViewById(R.id.rvTodoList);


        // calendar 셋팅
        // custom top bar 사용하기 위해 hide 처리
        weekCalendar.setTopbarVisible(false);
        CalendarDay currentDate = weekCalendar.getCurrentDate();
        tvCurrentMonth.setText(currentDate.getYear() + "년 " + currentDate.getMonth() + "월");

        // 처음 들어왔을 때 오늘 날짜에 선택되어 있도록
        weekCalendar.setSelectedDate(LocalDate.now());

        // dot 표시할 날짜들
        ArrayList<CalendarDay> dotDates = new ArrayList<>();
        dotDates.add(CalendarDay.from(2020, 7,5));
        dotDates.add(CalendarDay.from(2020, 7,6));
        dotDates.add(CalendarDay.from(2020, 7,7));
        dotDates.add(CalendarDay.from(2020, 7,8));

        // add decorator
        // 일요일 표시, 토요일 표시, dot 표시
        weekCalendar.addDecorator(new SaturdayDecorator());
        weekCalendar.addDecorator(new SundayDecorator(context));
        weekCalendar.addDecorator(new DotDecorator(ContextCompat.getColor(context, R.color.materialCalDotColor), dotDates));

        // week change 리스너
        weekCalendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                weekCalendar.setSelectedDate(date.getDate());
                tvCurrentMonth.setText(date.getYear() + "년 " + date.getMonth() + "월");
            }
        });







        // 리사이클러뷰 셋팅
        rvTodoList.setHasFixedSize(true);
        todoLayoutManager = new LinearLayoutManager(context);
        rvTodoList.setLayoutManager(todoLayoutManager);

        todoList = new ArrayList<>();
        todoList.add(new Todo(false, "할 일 1"));
        todoList.add(new Todo(false, "할 일 2"));
        todoList.add(new Todo(true, "할 일 3"));
        todoList.add(new Todo(false, "할 일 4"));

        todoAdapter = new TodoAdapter(context, todoList);
        rvTodoList.setAdapter(todoAdapter);
    }
}