package com.yanghyeonjin.calendar.weekcalendar;

import android.content.Context;
import android.text.style.ForegroundColorSpan;

import androidx.core.content.ContextCompat;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.yanghyeonjin.calendar.R;

import org.threeten.bp.DayOfWeek;

public class SundayDecorator implements DayViewDecorator {
    private Context context;

    public SundayDecorator(Context context) {
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        final DayOfWeek weekDay = day.getDate().getDayOfWeek();
        return weekDay == DayOfWeek.SUNDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.sundayColor)));
    }
}
