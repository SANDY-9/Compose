package com.mildfist.animatedwaterbottle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mildfist.animatedwaterbottle.ui.theme.AnimatedWaterBottleTheme
import kotlin.reflect.KProperty

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedWaterBottleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {

                    var usedAmount by remember {
                        mutableStateOf(400)
                    }

                    val totalWaterAmount = remember {
                        2400
                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        WaterBottle(
                            modifier = Modifier.width(250.dp),
                            totalWaterAmount = totalWaterAmount,
                            unit = "mL",
                            usedWaterAmount = usedAmount
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "Total amount is : $totalWaterAmount")
                        Button(
                            onClick = { usedAmount += 200 }
                        ) {
                            Text(text = "Drink")
                        }
                    }
                }
            }
        }
    }
}
