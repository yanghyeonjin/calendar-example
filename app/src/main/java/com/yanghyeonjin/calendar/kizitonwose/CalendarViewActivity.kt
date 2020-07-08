package com.yanghyeonjin.calendar.kizitonwose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.jakewharton.threetenabp.AndroidThreeTen
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.yanghyeonjin.calendar.R
import kotlinx.android.synthetic.main.activity_calendar_view.*
import org.threeten.bp.YearMonth
import org.threeten.bp.temporal.WeekFields
import java.util.*

class CalendarViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_view)

        AndroidThreeTen.init(this)


        // day
        calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View): DayViewContainer {
                return DayViewContainer(view)
            }

            // Called every time we need to reuse a container.
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                // 일자 표시
                container.textView.text = day.date.dayOfMonth.toString()

                // 현재 달에 포함되는 일자와 이전/다음 달에 포함되는 일자의 색 구분
                if (day.owner == DayOwner.THIS_MONTH) {
                    container.textView.setTextColor(ContextCompat.getColor(this@CalendarViewActivity, R.color.calDayOfThisMonthColor))
                } else {
                    container.textView.setTextColor(ContextCompat.getColor(this@CalendarViewActivity, R.color.calDayOfOtherMonthColor))
                }
            }
        }


        // month header
        calendarView.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthHeaderViewContainer> {
            override fun create(view: View): MonthHeaderViewContainer {
                return MonthHeaderViewContainer(view)
            }

            override fun bind(container: MonthHeaderViewContainer, month: CalendarMonth) {
                container.tvHeaderMonth.text = "${month.yearMonth.year}년 ${month.yearMonth.monthValue}월"
            }
        }

        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(10)
        val lastMonth = currentMonth.plusMonths(10)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        calendarView.setup(firstMonth, lastMonth, firstDayOfWeek)
        calendarView.scrollToMonth(currentMonth)
    }
}