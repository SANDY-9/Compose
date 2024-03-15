package com.example.calendar.model

import com.example.calendar.toDayOfWeekName
import com.example.calendar.toHour
import com.example.calendar.toMonth
import java.time.LocalDate

data class Date(
    val year: Int = LocalDate.now().year,
    val month: Int = LocalDate.now().monthValue,
    val day: Int = LocalDate.now().dayOfMonth,
    val dayOfWeek: Int = LocalDate.of(year, month, day).dayOfWeek.value,
    val over: Boolean = false
) {
    fun dayStr(): String {
        return "${month.toMonth()}월 ${day.toHour()}일(${dayOfWeek.toDayOfWeekName()})"
    }
}
