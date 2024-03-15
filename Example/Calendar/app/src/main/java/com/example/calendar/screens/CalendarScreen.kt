package com.example.calendar.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calendar.screens.component.Header
import com.example.calendar.screens.component.Title
import com.example.calendar.screens.component.ContentPager

@Composable
fun CalendarScreen(
    calendarViewModel: CalendarViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val state by calendarViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            state = state,
            onRefresh = {
                calendarViewModel.onEvent(CalendarUiEvent.OnCalendarRefreshed)
            }
        )
        Header()
        ContentPager(
            state = state,
            onPageChanged = { page ->
                calendarViewModel.onEvent(CalendarUiEvent.OnPageChanged(page))
            },
            onPicked = { date ->
                calendarViewModel.onEvent(CalendarUiEvent.OnDatePicked(date))
            }
        )
    }

}