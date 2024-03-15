package com.example.calendar.screens.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.calendar.R
import com.example.calendar.ui.theme.Pink80
import com.example.calendar.ui.theme.TypoType
import com.example.calendar.ui.theme.typography

/**
 * 달력 헤더(요일) 영역
 *
 * @param modifier
 * @param dayOfWeek : 월,화,수,목,금,토
 */
@Composable
fun Header(
    modifier: Modifier = Modifier,
    dayOfWeek: Array<String> = stringArrayResource(id = R.array.day_of_the_week)
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        repeat(dayOfWeek.size) { index ->
            Text(
                modifier = modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = typography(textStyle = TypoType.BodyBold),
                color = Color.Gray,
                text = dayOfWeek[index]
            )
        }
    }
}
