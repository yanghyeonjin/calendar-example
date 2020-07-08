package com.yanghyeonjin.calendar;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

public class MultipleEventDecorator implements DayViewDecorator {

    private final int[] colors;
    private final HashSet<CalendarDay> dates;


    public MultipleEventDecorator(int[] colors, Collection<CalendarDay> dates) {
        this.colors = colors;
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new CustomMultipleDotSpan(10, colors));
    }
}
