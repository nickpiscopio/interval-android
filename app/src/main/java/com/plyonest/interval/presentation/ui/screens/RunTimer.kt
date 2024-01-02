package com.plyonest.interval.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.plyonest.interval.domain.interfaces.TimerStateInterface
import com.plyonest.interval.presentation.constant.DIMEN_25
import com.plyonest.interval.presentation.ui.theme.IntervalTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun RunTimer(viewModel: RunTimerViewModel = koinViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = DIMEN_25),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Run Timer Screen"
        )

        Button(onClick = {
            viewModel.print()
        }) {
            Text("Click me")
        }
    }
}

class RunTimerViewModel(
    private val timerState: TimerStateInterface
): ViewModel() {

    fun print() {
        Log.d("Interval", timerState.getState().toString())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRunTimer() {
    IntervalTheme {
        RunTimer(koinViewModel())
    }
}