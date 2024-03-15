package com.example.calendar.screens.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.calendar.model.Date
import com.example.calendar.screens.CalendarUiState
import com.example.calendar.screens.Page
import com.example.calendar.ui.theme.Pink80
import com.example.calendar.ui.theme.TypoType
import com.example.calendar.ui.theme.typography

/**
 * 달력 내용 영역(day, 일)
 *
 * @param state
 * @param onPageChanged 페이지가 사용자의 동작에 의해 바뀔 때 호출되는 콜백 함수(페이지를 매개변수로 전달)
 * @param onPicked 날짜를 클릭(pick)했을 때 이벤트 콜백
 * @param modifier
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentPager(
    state: CalendarUiState,
    onPageChanged: (Int) -> Unit,
    onPicked: (Date) -> Unit,
    modifier: Modifier = Modifier
) {

    val pagerState = rememberPagerState(
        initialPage = state.initialPage
    ) {
        state.totalPage
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.settledPage }.collect { page ->
            onPageChanged(page)
            pagerState.scrollToPage(1)
        }
    }

    HorizontalPager(state = pagerState) { page ->
        val item = when(page) {
            Page.Page0.page -> state.prevItem
            Page.Page1.page -> state.currentItem

            // page 2
            else -> state.nextItem
        }
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            val weeks = item.weeks
            repeat(weeks.size) { index ->
                MonthCalendarContentRow(
                    item = weeks[index],
                    pickedDate = state.pickedDate,
                    onPicked = onPicked
                )
            }
        }
    }
}

@Composable
private fun MonthCalendarContentRow(
    modifier: Modifier = Modifier,
    item: List<Date>,
    pickedDate: Date,
    onPicked: (Date) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(item.size) { index ->
            val date = item[index]
            val picked = pickedDate.day == date.day && pickedDate.month == date.month && pickedDate.year == date.year
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text(
                    modifier = modifier
                        .align(Alignment.Center)
                        .size(30.dp)
                        .background(
                            color = if (picked) Pink80 else Color.Transparent,
                            shape = CircleShape
                        )
                        .padding(5.dp)
                        .clickable(enabled = !date.over) {
                           onPicked(date)
                        },
                    textAlign = TextAlign.Center,
                    style = typography(textStyle = TypoType.Body),
                    color = when {
                        picked -> Color.White
                        else -> Color.Black
                    },
                    text = if(!date.over) "${date.day}" else ""
                )
            }
        }
    }
}