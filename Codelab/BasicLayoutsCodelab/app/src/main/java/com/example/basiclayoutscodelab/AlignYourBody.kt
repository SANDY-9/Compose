package com.example.basiclayoutscodelab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        // Arrangement.spacedBy() 메서드를 사용하여 각 하위 컴포저블 사이에 고정된 공간을 추가
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        // LazyRow의 측면에 패딩을 추가
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(10) { item ->
            AlignYourBodyElement(
                "Inversions"
            )
        }
    }
}

@Composable
fun AlignYourBodyElement(
    str: String,
    modifier: Modifier = Modifier
) {
    /*
    - Column 하위 요소 정렬(align) - 가로(LinearLayout horizontal)
    Start
    CenterHorizontally
    End
     */
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier
                .size(88.dp)
                .clip(CircleShape)
            ,
            painter = painterResource(R.drawable.image),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            modifier = modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            text = str
        )
    }
    /*
     - Row 하위 요소 정렬(align) - 세로(LinearLayout vertical)
    Top
    CenterVertically
    Bottom
     */
    /*
    Box 하위 요소 정렬(align) - 가로, 세로 (FrameLayout)
    TopStart
    TopCenter
    TopEnd
    CenterStart
    Center
    CenterEnd
    BottomStart
    BottomCenter
    BottomEnd
     */
}

@Preview(name = "AlignYourBodyElement")
@Composable
private fun PreviewAlignYourBodyElement() {
    AlignYourBodyRow()
    //AlignYourBodyElement()
}