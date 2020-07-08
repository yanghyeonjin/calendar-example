package com.yanghyeonjin.calendar;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

public class EventDecorator implements DayViewDecorator {
    // private final int color;
    private final int[] colors;
    private final HashSet<CalendarDay> dates;

    public EventDecorator(int[] colors, Collection<CalendarDay> dates) {
        // this.color = color;
        this.colors = colors;
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new CustomMultipleDotSpan(5, colors));
    }
}
