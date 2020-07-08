package com.yanghyeonjin.calendar.kizitonwose

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendarview.ui.ViewContainer
import kotlinx.android.synthetic.main.layout_calendar_day.view.*

class DayViewContainer(view: View) : ViewContainer(view) {
    val textView: TextView = view.calendarDayText
}