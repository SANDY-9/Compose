package com.example.calendar.screens

import com.example.calendar.model.Date


sealed class CalendarUiEvent {

    data class OnDatePicked(val date: Date): CalendarUiEvent()
    data class OnPageChanged(val page: Int): CalendarUiEvent()
    data object OnCalendarRefreshed: CalendarUiEvent()

}