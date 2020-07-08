package com.yanghyeonjin.calendar.kizitonwose

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.kizitonwose.calendarview.ui.ViewContainer
import kotlinx.android.synthetic.main.layout_calendar_header.view.*

class MonthViewContainer(view: View) : ViewContainer(view) {
    val btnPrevMonth: Button = view.btnPrevMonth
    val btnNextMonth: Button = view.btnNextMonth
    val tvHeaderMonth: TextView = view.tvHeaderMonth
}