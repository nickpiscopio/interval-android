package com.plyonest.interval

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.plyonest.interval.ui.theme.IntervalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntervalTheme {
                App()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    IntervalTheme {
        App()
    }
}