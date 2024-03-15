package com.example.calendar.screens

import androidx.lifecycle.ViewModel
import com.example.calendar.CalendarUtils
import com.example.calendar.model.Date
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalendarViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(CalendarUiState())
    val uiState: StateFlow<CalendarUiState> = _uiState.asStateFlow()

    fun onEvent(event: CalendarUiEvent) {
        when(event) {
            is CalendarUiEvent.OnDatePicked -> updateDatePicked(event.date)
            is CalendarUiEvent.OnPageChanged -> updateItemState(event.page)
            CalendarUiEvent.OnCalendarRefreshed -> refreshItemState()
        }
    }

    private fun updateDatePicked(date: Date) {
        _uiState.update {
            it.copy(
                pickedDate = date
            )
        }
    }

    private fun updateItemState(page: Int) {
        when(page) {
            0 -> addPrevItem()
            2 -> addNextItem()
        }
    }

    private fun addPrevItem() {
        _uiState.update {
            val baseDate = it.currentItemDate.minusMonths(1)
            it.copy(
                prevItem = CalendarUtils.getCalendar(baseDate.minusMonths(1)),
                currentItem = CalendarUtils.getCalendar(baseDate),
                nextItem = CalendarUtils.getCalendar(baseDate.plusMonths(1)),
                currentItemDate = baseDate
            )
        }
    }

    private fun addNextItem() {
        _uiState.update {
            val baseDate = it.currentItemDate.plusMonths(1)
            it.copy(
                prevItem = CalendarUtils.getCalendar(baseDate.minusMonths(1)),
                currentItem = CalendarUtils.getCalendar(baseDate),
                nextItem = CalendarUtils.getCalendar(baseDate.plusMonths(1)),
                currentItemDate = baseDate
            )
        }
    }

    private fun refreshItemState() {
        _uiState.update {
            val baseDate = CalendarUtils.getBaseDate()
            it.copy(
                prevItem = CalendarUtils.getCalendar(baseDate.minusMonths(1)),
                currentItem = CalendarUtils.getCalendar(baseDate),
                nextItem = CalendarUtils.getCalendar(baseDate.plusMonths(1)),
                currentItemDate = baseDate,
                pickedDate = CalendarUtils.getToday()
            )
        }
    }


}