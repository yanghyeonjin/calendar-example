package com.yanghyeonjin.calendar.materialcalendarview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.rd.PageIndicatorView;
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
    private PageIndicatorView pageIndicatorView;

    private TextView tvCurrentMonth;
    private Button btnAddEvent;

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
        pageIndicatorView = findViewById(R.id.pageIndicator);
        tvCurrentMonth = findViewById(R.id.tvCurrentMonth);
        btnAddEvent =  findViewById(R.id.btnAddEvent);


        // 리사이클러뷰 셋팅
        // rvEvents.setHasFixedSize(true);
        // gridLayoutManager = new GridLayoutManager(context, 2);
        // rvEvents.setLayoutManager(gridLayoutManager);

        events = new ArrayList<>();
        events.add(new Event("이벤트1"));
        events.add(new Event("이벤트2"));


        // eventAdapter = new EventAdapter(events, context);
        // rvEvents.setAdapter(eventAdapter);
        eventAdapter = new EventAdapter(context, events, pageIndicatorView, discreteScrollView);


        // DiscreteScrollView
        discreteScrollView.setAdapter(eventAdapter);
        discreteScrollView.setSlideOnFling(true); // 슬라이드 한 번 했을 때, 여러 개 아이템 슬라이드 되도록 설정
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
        .setMaxScale(1.05f)
        .setMinScale(0.8f)
        .setPivotX(Pivot.X.CENTER)
        .setPivotY(Pivot.Y.BOTTOM)
        .build());
        discreteScrollView.addScrollStateChangeListener(new DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder>() {
            @Override
            public void onScrollStart(@NonNull RecyclerView.ViewHolder viewHolder, int adapterPosition) {
                //called when scroll is started, including programmatically initiated scroll
            }

            @Override
            public void onScrollEnd(@NonNull RecyclerView.ViewHolder viewHolder, int adapterPosition) {
                //called when scroll ends

                // 다음으로 넘어가려다가 못넘어갔을 때 indicator 처리
                pageIndicatorView.setSelection(adapterPosition);
            }

            /**
             * Called when scroll is in progress.
             * @param scrollPosition is a value inside the interval [-1f..1f], it corresponds to the position of currentlySelectedView.
             * In idle state:
             * |view1|  |currentlySelectedView|  |view2|
             * -view1 is on position -1;
             * -currentlySelectedView is on position 0;
             * -view2 is on position 1.
             * @param currentIndex - index of current view
             * @param newIndex - index of a view which is becoming the new current
             * @param currentHolder - ViewHolder of a current view
             * @param newCurrentHolder - ViewHolder of a view which is becoming the new current
             */
            @Override
            public void onScroll(float scrollPosition, int currentIndex, int newIndex, @Nullable RecyclerView.ViewHolder currentHolder, @Nullable RecyclerView.ViewHolder newCurrentHolder) {
                // 스크롤 시, 아이템 위치에 맞게 page indicator 설정
                if (newCurrentHolder != null) {
                    pageIndicatorView.setSelection(newCurrentHolder.getAdapterPosition());
                }
            }
        });
        discreteScrollView.setItemTransitionTimeMillis(500);


        // page indicator
        pageIndicatorView.setCount(events.size());



        // 타이틀 포맷
//        TitleFormatter titleFormatter = new TitleFormatter() {
//            @Override
//            public CharSequence format(CalendarDay day) {
//                return day.getYear() + "년 " + day.getMonth() + "월";
//            }
//        };
//        materialCalendar.setTitleFormatter(titleFormatter);

        // custom top bar 사용하기 위해 hide 처리
        materialCalendar.setTopbarVisible(false);
        CalendarDay currentDate = materialCalendar.getCurrentDate();
        tvCurrentMonth.setText(currentDate.getYear() + "년 " + currentDate.getMonth() + "월");


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

                // page indicator 숫자 동기화
                pageIndicatorView.setCount(events.size());
            }
        });

        // 처음 들어왔을 때 오늘 날짜에 선택되어 있도록
        materialCalendar.setSelectedDate(LocalDate.now());


        // month change 리스너
        materialCalendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                materialCalendar.setSelectedDate(date.getDate());
                tvCurrentMonth.setText(date.getYear() + "년 " + date.getMonth() + "월");
            }
        });



        // 이벤트 추가 버튼 클릭
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new BottomSheetDialog(context);
                dialog.setContentView(R.layout.dialog_bottom_sheet);
                dialog.show();
            }
        });
    }
}