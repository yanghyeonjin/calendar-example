package com.yanghyeonjin.calendar;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class CurrentDayDecorator implements DayViewDecorator {

    private Drawable drawable;
    CalendarDay myDay;

    public CurrentDayDecorator(Activity context, CalendarDay currentDay) {
        this.myDay = currentDay;

        // you can set background for Decorator via drawable here
        drawable = ContextCompat.getDrawable(context, R.drawable.ic_launcher_background);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day == myDay;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}
