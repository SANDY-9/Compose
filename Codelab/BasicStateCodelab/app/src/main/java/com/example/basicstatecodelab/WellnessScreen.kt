package com.example.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
    // count 변수에 다른 값을 설정해도 Compose에서 이 값을 상태 변경으로 감지하지 않아 아무 일도 일어나지 않음
    // 상태가 변경될 때 Compose에 화면을 다시 그려야 한다고(리컴포저블) 알리지 않았기 때문
    var count = 0
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "You've had $count glasses."
        )
        Button(
            onClick = { count++ },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add one")
        }
    }
}

@Preview
@Composable
fun Preview() {
    WellnessScreen()
}