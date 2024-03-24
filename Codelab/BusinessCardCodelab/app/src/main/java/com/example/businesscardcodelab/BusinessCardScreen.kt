package com.example.businesscardcodelab

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.businesscardcodelab.ui.theme.BusinessCardCodelabTheme
import com.example.businesscardcodelab.ui.theme.DarkGreen
import com.example.businesscardcodelab.ui.theme.FlatGreen
import com.example.businesscardcodelab.ui.theme.TypoGreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BusinessCardScreen(
    modifier: Modifier = Modifier
) {
    Surface {
        Column {
            BusinessNamingCard(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                name = "Leem SunMi",
                grade = "Android Junior Developer"
            )
            BusinessInformation(
                icons = listOf(
                    Icons.Filled.Call,
                    Icons.Filled.Share,
                    Icons.Filled.Email
                ),
                contents = listOf(
                    "+11 (123) 444 555 666",
                    "@AndroidDev",
                    "sandy@android.com"
                )
            )
        }
    }
}

@Composable
private fun BusinessNamingCard(
    modifier: Modifier = Modifier,
    name: String,
    grade: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(150.dp)
                .background(color = DarkGreen, shape = RectangleShape)
                .padding(16.dp),
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = null
        )
        Text(
            text = name,
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Light
        )
        Text(
            text = grade,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Black,
            color = FlatGreen
        )
    }
}

@Composable
private fun BusinessInformation(
    icons: List<ImageVector>,
    contents: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(3) { index ->
            BusinessInformItem(
                icon = icons[index],
                content = contents[index]
            )
        }
    }
}


@Composable
private fun BusinessInformItem(
    icon: ImageVector,
    content: String
) {
    Row(
        modifier = Modifier.padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            tint = FlatGreen,
            contentDescription = null
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = content,
            color = TypoGreen
        )
    }
}



@Preview(name = "BusinessCardScreen")
@Composable
private fun PreviewBusinessCardScreen() {
    BusinessCardCodelabTheme {
        BusinessCardScreen()
    }
}