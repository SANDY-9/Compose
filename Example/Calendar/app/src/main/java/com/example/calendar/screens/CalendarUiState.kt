package com.example.calendar.screens

import com.example.calendar.CalendarUtils
import com.example.calendar.model.Calendar
import com.example.calendar.model.Date
import java.time.LocalDate

data class CalendarUiState(
    val currentItem: Calendar = CalendarUtils.getCalendar(),
    val prevItem: Calendar = CalendarUtils.getCalendar(LocalDate.now().minusMonths(1)),
    val nextItem: Calendar = CalendarUtils.getCalendar(LocalDate.now().plusMonths(1)),
    val currentItemDate: LocalDate = LocalDate.now(),
    val currentPage: Int = 1,
    val pickedDate: Date = Date(),
    val visible: Boolean = false,
    val initialPage: Int = 1,
    val totalPage: Int = 3
)

enum class Page(val page: Int = 0) {
    Page0(0), Page1(1), Page2(2)
}