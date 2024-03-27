package com.example.basicstatecodelab

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


//재사용성이 높아지기 때문에 모든 Composable에 기본 Modifier를 제공하는 것이 좋다.
//모든 필수 매개변수 다음에 첫 번째 옵션 매개변수로 표시되어야함
@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    WaterCounter(modifier)
}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    val count = 0
    Text(
        modifier = modifier.padding(16.dp),
        text = "You've had $count glasses."
    )
}

@Preview
@Composable
fun Preview() {
    WellnessScreen()
}