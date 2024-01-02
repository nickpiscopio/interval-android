package com.plyonest.interval.presentation.ui.screens

import android.app.Application
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.plyonest.interval.R
import com.plyonest.interval.data.TimerState
import com.plyonest.interval.domain.interfaces.TimerStateInterface
import com.plyonest.interval.presentation.constant.DIMEN_25
import com.plyonest.interval.presentation.ui.theme.IntervalTheme
import com.plyonest.interval.presentation.utils.HoursMinutesSeconds
import com.plyonest.interval.presentation.utils.TimeUtil
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
            text = viewModel.currentTitle.value
        )

        Timer(viewModel)

        Button(onClick = {
            viewModel.print()
        }) {
            Text("Pause")
        }
    }
}

@Composable
fun Timer(viewModel: RunTimerViewModel) {
    Row {
        if (!viewModel.timeLeft.value.areHoursEmpty()) {
            Text(
                text = viewModel.timeLeft.value.getHours()
            )
            Text(
                text = "h"
            )
        }

        if (!viewModel.timeLeft.value.areMinutesEmpty()) {
            Text(
                text = viewModel.timeLeft.value.getMinutes()
            )
            Text(
                text = "m"
            )
        }

        Text(
            text = viewModel.timeLeft.value.getSeconds()
        )
        Text(
            text = "s"
        )
    }
}

class RunTimerViewModel(
    private val application: Application,
    private val timerState: TimerStateInterface
): ViewModel() {
    var currentIntervalIndex: Int = 0
    var currentTitle: MutableState<String> = mutableStateOf("")
    var timeLeft: MutableState<HoursMinutesSeconds> = mutableStateOf(HoursMinutesSeconds("00", "00",  "00"))
    private lateinit var timer: CountDownTimer

    val beep2 = MediaPlayer.create(application.applicationContext, R.raw.beep_2)

    init {
        runTimer()
    }

    private fun runTimer() {
        val currentInterval = timerState.getState().intervals[currentIntervalIndex]
        val currentDurationInMillis = currentInterval.durationInMillis
        val countDownInterval: Long = 1000
        currentTitle.value = currentInterval.name
        setTimeLeft(currentDurationInMillis)

        // TODO: Check to make sure the countDownInterval should be added to the currentDurationInMillis.
        timer = object : CountDownTimer(currentDurationInMillis + countDownInterval, countDownInterval) {
            override fun onTick(durationLeftInMillis: Long) {
                setTimeLeft(durationLeftInMillis)
            }

            override fun onFinish() {
                currentIntervalIndex++
                runTimer()
            }
        }
        startTimer()
    }

    fun pauseTimer() {
        // TODO: Check if this deletes the entire time.
        timer.cancel()
    }

    fun startTimer() {
        timer.start()
    }

    fun playSound() {
        // TODO: Play sounds based on interval.
        beep2.start()
    }

    private fun setTimeLeft(durationInMillis: Long) {
        timeLeft.value = TimeUtil.convertTimeAsMillisToHms(durationInMillis)
        if (timeLeft.value.areHoursEmpty() && timeLeft.value.areMinutesEmpty() && timeLeft.value.getSeconds().toLong() < 1) {
            playSound()
        }
    }

    fun print() {
        Log.d("Interval", timerState.getState().toString())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRunTimer() {
    IntervalTheme {
        RunTimer(viewModel = RunTimerViewModel(Application(), TimerState()))
    }
}