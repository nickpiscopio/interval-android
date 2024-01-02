package com.plyonest.interval.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.plyonest.interval.presentation.ui.theme.IntervalTheme

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntervalTheme {
                MainCompose()
            }
        }
    }
}