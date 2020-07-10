package com.yanghyeonjin.calendar.weekcalendar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import org.threeten.bp.DayOfWeek;

public class SaturdayDecorator implements DayViewDecorator {
    private final Drawable highlightDrawable;
    private static final int satColor = Color.parseColor("#0000FF");

    public SaturdayDecorator() {
        highlightDrawable = new ColorDrawable(satColor);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        final DayOfWeek weekDay = day.getDate().getDayOfWeek();
        return weekDay == DayOfWeek.SATURDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(highlightDrawable);
    }
}
