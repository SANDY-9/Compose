package com.example.calendar

import com.example.calendar.model.Calendar
import com.example.calendar.model.Date
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.Locale

object CalendarUtils {

    fun getCalendar(baseDate: LocalDate = LocalDate.now()): Calendar {

        val firstDayOfMonth = LocalDate.of(baseDate.year, baseDate.month, 1)
        val lastDayOfMonth = LocalDate.of(baseDate.year, baseDate.month, baseDate.lengthOfMonth())
        val firstSunday = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        val lastSaturday = lastDayOfMonth.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))

        val weeks =  mutableListOf<Date>().apply {
            var date = firstSunday
            while (date <= lastSaturday) {
                add(
                    Date(
                        year = date.year,
                        month = date.monthValue,
                        day = date.dayOfMonth,
                        dayOfWeek = date.dayOfWeek.value,
                        over = date.month != baseDate.month
                    )
                )
                date = date.plusDays(1)
            }
        }.chunked(7)

        return Calendar(
            year = baseDate.year,
            month = baseDate.monthValue,
            weeks = weeks
        )
    }

    fun getBaseDate(date: Date = Date()): LocalDate = LocalDate.of(date.year, date.month, date.day)

    fun getToday(): Date {
        val today = LocalDate.now()
        return Date(
            year = today.year,
            month = today.monthValue,
            day = today.dayOfMonth,
            dayOfWeek = today.dayOfWeek.value,
            over = false
        )
    }

    fun getPageWeekOfMonth(date: LocalDate = LocalDate.now()): Int {
        val weekFields = WeekFields.of(Locale.getDefault())

        // week field값은 1부터 시작이지만 position값은 0부터 시작하므로 1을 빼줘야 한다.
        return date.get(weekFields.weekOfMonth()) - 1
    }

}