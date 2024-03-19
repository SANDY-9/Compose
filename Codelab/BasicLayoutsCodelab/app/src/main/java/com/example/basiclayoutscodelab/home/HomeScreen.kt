package com.example.basiclayoutscodelab.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiclayoutscodelab.home.components.AlignYourBodyRow
import com.example.basiclayoutscodelab.home.components.FavoriteCollectionsGrid
import com.example.basiclayoutscodelab.home.section.HomeSection
import com.example.basiclayoutscodelab.home.components.SearchBar

/*
  Lazy 레이아웃은 자동으로 스크롤 동작이 추가되어있음.
  일반적으로, 목록에 포함된 요소가 많거나 로드해야 할 데이터 세트가 많아서
  모든 항목을 동시에 내보내면 성능이 저하되고 앱이 느려지게 되는 경우에 Lazy 레이아웃을 사용
  목록에 포함된 요소의 개수가 많지 않은 경우에는 간단한 Column 또는 Row를 사용하고 스크롤 동작을 수동으로 추가
  (modifier.verticalScroll / modifier.horizontalScroll)
 */

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        // 스크롤 상태를 수정할 필요가 없으므로 rememberScrollState를 사용
        //ScrollState : 스크롤의 현재 상태를 포함하며 외부에서 스크롤 상태를 수정하는 데 사용
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = "AlignYourBody") {
            AlignYourBodyRow()
        }
        HomeSection(title = "Favorite Collections") {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 300)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}