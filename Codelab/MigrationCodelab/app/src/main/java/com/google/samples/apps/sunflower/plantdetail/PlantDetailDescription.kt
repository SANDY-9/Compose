/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.plantdetail

import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.viewmodels.PlantDetailViewModel

@Composable
fun PlantDetailDescription(plantDetailViewModel: PlantDetailViewModel) {

    //LiveData.observeAsState()는 뷰모델의 LiveData 관찰을 시작하고 값을 State 객체로 표현
    val plant by plantDetailViewModel.plant.observeAsState()

    plant?.let { plant ->
        PlantDetailContent(plant)
    }
}

@Composable
fun PlantDetailContent(plant: Plant) {
    // ConstraintLayout migration
    Surface {
        Column(Modifier.padding(dimensionResource(R.dimen.margin_normal))) {
            PlantName(plant.name)
            PlantWatering(plant.wateringInterval)
            PlantDescription(plant.description)
        }
    }
}

// wrapContentWidth : 정렬(alignment) 기능 추가 가능
@Composable
private fun PlantName(name: String) {
    Text(
        style = MaterialTheme.typography.h5,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.margin_small))
            .wrapContentWidth(Alignment.CenterHorizontally),
        text = name
    )
}

@Composable
private fun PlantWatering(wateringInterval: Int) {
    Column(Modifier.fillMaxWidth()) {
        // Modifier.align() : Column, Box, Row와 같은 레이아웃 안에 있는 컴포넌트이어야 사용 가능한 속성
        val centerWithPaddingModifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.margin_small))
            .align(Alignment.CenterHorizontally)

        val normalPadding = dimensionResource(R.dimen.margin_normal)

        Text(
            text = stringResource(R.string.watering_needs_prefix),
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold,
            modifier = centerWithPaddingModifier.padding(top = normalPadding)
        )

        val wateringIntervalText = pluralStringResource(
            R.plurals.watering_needs_suffix, wateringInterval, wateringInterval
        )

        Text(
            text = wateringIntervalText,
            modifier = centerWithPaddingModifier.padding(bottom = normalPadding)
        )

    }
}

// Compose는 현재 Spanned 클래스를 지원하지 않으며 HTML 형식 텍스트도 표시하지 않는다.
// 따라서 이 제한을 우회하려면 Compose 코드에서 뷰 시스템의 TextView를 사용해야 한다.
// Compose는 아직 HTML 코드를 렌더링할 수 없으므로 TextView를 만들어 AndroidView API를 사용하여 정확히 렌더링을 해야함
// XML 파일에서 뷰를 확장하려는 경우 androidx.compose.ui:ui-viewbinding 라이브러리의 AndroidViewBinding API와 함께 뷰 결합을 사용
@Composable
private fun PlantDescription(description: String) {

    // Remembers the HTML formatted description.
    // htmlDescription이 변경되면 AndroidView 업데이트 콜백이 재구성
    val htmlDescription = remember(description) {
        HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    // AndroidView는 뷰 1개만 가능
    AndroidView(
        // factory에 뷰 넣기
        factory = { context ->
            TextView(context).apply {
                movementMethod = LinkMovementMethod.getInstance()
            }
        },
        // 뷰 변경 콜백
        update = {
            it.text = htmlDescription
        }
    )
}

