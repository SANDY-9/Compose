package com.example.calendar.screens.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calendar.screens.CalendarUiState
import com.example.calendar.toMonth
import com.example.calendar.ui.theme.TypoType
import com.example.calendar.ui.theme.typography

/**
 * 달력 제목 영역 (년+월)
 *
 * @param state : MonthCalendarUiState
 * @param onRefresh : "오늘 날짜 데이터로 롤백"을 전달하는 이벤트 콜백
 * @param modifier
 */
@Composable
fun Title(
    state: CalendarUiState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        Text(
            modifier = modifier.align(
                Alignment.Center
            ),
            style = typography(textStyle = TypoType.Title),
            text = "${state.currentItem.year}년 ${state.currentItem.month.toMonth()}월"
        )
        OutlinedButton(
            modifier = modifier
                .height(30.dp)
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp),
            border = BorderStroke(1.dp, color = Color.LightGray),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Gray
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
            onClick = onRefresh
        ) {
            Text(
                style = typography(textStyle = TypoType.BodyBold),
                text = CALENDAR_RESET_BUTTON_TITLE
            )
        }
    }
}

private const val CALENDAR_RESET_BUTTON_TITLE = "오늘로"