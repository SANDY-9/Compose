package com.example.basiclayoutscodelab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
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
fun AlignYourBodyElement(
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
            text = "Inversions"
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
    AlignYourBodyElement()
}