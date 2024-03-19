package com.example.basiclayoutscodelab

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.basiclayoutscodelab.home.HomeScreen
import com.example.basiclayoutscodelab.ui.bottom_nav.AppBottomNavigation
import com.example.basiclayoutscodelab.ui.bottom_nav.AppNavigationRail
import com.example.basiclayoutscodelab.ui.theme.BasicLayoutsCodelabTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //import androidx.compose.material3.windowsizeclass.WindowSizeClass
            //import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
            //import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
            val windowSizeClass = calculateWindowSizeClass(this)
            MyApp(windowSizeClass)
        }
    }
}

@Composable
fun MyApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MyAppPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            MyAppLandscape()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyAppPortrait() {
    BasicLayoutsCodelabTheme {
        Scaffold(
            bottomBar = { AppBottomNavigation() },
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ) {
            HomeScreen()
        }
    }
}

@Composable
fun MyAppLandscape() {
    BasicLayoutsCodelabTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceVariant) {
            Row {
                AppNavigationRail()
                HomeScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    //MyAppPortrait()
    //MyAppLandscape()
}