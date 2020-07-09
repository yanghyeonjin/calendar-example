package com.yanghyeonjin.calendar.materialcalendarview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.yanghyeonjin.calendar.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;

public class MaterialCalendarActivity extends AppCompatActivity {

    private MaterialCalendarView materialCalendar;

    private static final String TAG = "MainActivity";
    private Context context;
    private Activity activity;

    // private RecyclerView rvEvents;
    // private RecyclerView.LayoutManager gridLayoutManager;
    private RecyclerView.Adapter eventAdapter;
    private ArrayList<Event> events;

    private DiscreteScrollView discreteScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_calendar);

        context = MaterialCalendarActivity.this;
        activity = MaterialCalendarActivity.this;


        // 아이디 연결
        materialCalendar = findViewById(R.id.calendarView);
        // rvEvents = findViewById(R.id.rvEvents);
        discreteScrollView = findViewById(R.id.picker);


        // 리사이클러뷰 셋팅
        // rvEvents.setHasFixedSize(true);
        // gridLayoutManager = new GridLayoutManager(context, 2);
        // rvEvents.setLayoutManager(gridLayoutManager);

        events = new ArrayList<>();
        events.add(new Event("이벤트1"));
        events.add(new Event("이벤트2"));
        events.add(new Event("이벤트3"));


        eventAdapter = new EventAdapter(events, context);
        // rvEvents.setAdapter(eventAdapter);


        // DiscreteScrollView
        discreteScrollView.setAdapter(eventAdapter);
        discreteScrollView.setSlideOnFling(true); // 슬라이드 한 번 했을 때, 여러 개 아이템 슬라이드 되도록 설정
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
        .setMaxScale(1.05f)
        .setMinScale(0.8f)
        .setPivotX(Pivot.X.CENTER)
        .setPivotY(Pivot.Y.BOTTOM)
        .build());


        // 타이틀 포맷
        TitleFormatter titleFormatter = new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                return day.getYear() + "년 " + day.getMonth() + "월";
            }
        };
        materialCalendar.setTitleFormatter(titleFormatter);


        // add multiple event dots per day
//        CalendarDay day1 = CalendarDay.from(2020, 7,1);
//        CalendarDay day2 = CalendarDay.from(2020, 7,2);
//        ArrayList<CalendarDay> days = new ArrayList<>();
//        ArrayList<CalendarDay> days2 = new ArrayList<>();
//        days.add(day1);
//        days2.add(day2);
//        int[] threeColors = {Color.rgb(0, 0, 255), Color.rgb(0, 255, 0), Color.rgb(255, 0, 0)};
//        int[] twoColors = {Color.rgb(3, 100, 100), Color.rgb(5, 200, 0)};
//        calendarView.addDecorators(new MultipleEventDecorator(threeColors, days));
//        calendarView.addDecorators(new MultipleEventDecorator(twoColors, days2));


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
        materialCalendar.addDecorators(new OneEventDecorator(ContextCompat.getColor(context, R.color.materialCalDotColor), days5));


        // date click 리스너
        materialCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Event event = new Event(date.getDate().toString());
                events.add(event);
                eventAdapter.notifyDataSetChanged();
            }
        });

        // 처음 들어왔을 때 오늘 날짜에 선택되어 있도록
        materialCalendar.setSelectedDate(LocalDate.now());


        // month change 리스너
        materialCalendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                materialCalendar.setSelectedDate(date.getDate());
            }
        });
    }
}