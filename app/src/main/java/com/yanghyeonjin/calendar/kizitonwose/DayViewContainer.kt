package com.yanghyeonjin.calendar.kizitonwose

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendarview.ui.ViewContainer
import kotlinx.android.synthetic.main.calendar_day_layout.view.*

class DayViewContainer(view: View) : ViewContainer(view) {
    val textVIew: TextView = view.calendarDayText
}