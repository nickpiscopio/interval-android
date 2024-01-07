package com.plyonest.interval.presentation.ui.screens

import android.app.Application
import androidx.compose.foundation.background
import com.plyonest.interval.R

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.plyonest.interval.data.Navigator
import com.plyonest.interval.data.TimerState
import com.plyonest.interval.domain.interfaces.NavigatorInterface
import com.plyonest.interval.domain.interfaces.TimerStateInterface
import com.plyonest.interval.domain.models.Interval
import com.plyonest.interval.domain.models.IntervalTimer
import com.plyonest.interval.presentation.AppScreen
import com.plyonest.interval.presentation.constant.COLOR_BACKGROUND_SCREEN
import com.plyonest.interval.presentation.constant.COLOR_BLACK_20
import com.plyonest.interval.presentation.constant.COLOR_BLACK_80
import com.plyonest.interval.presentation.constant.COLOR_WHITE_100
import com.plyonest.interval.presentation.constant.DIMEN_10
import com.plyonest.interval.presentation.constant.DIMEN_15
import com.plyonest.interval.presentation.constant.DIMEN_25
import com.plyonest.interval.presentation.ui.fragments.IntervalButtonPrimary
import com.plyonest.interval.presentation.ui.fragments.IntervalInput
import com.plyonest.interval.presentation.ui.fragments.IntervalTextField
import com.plyonest.interval.presentation.ui.theme.IntervalTheme
import com.plyonest.interval.presentation.utils.TimeUtil
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateTimer(viewModel: CreateTimerViewModel = koinViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(COLOR_BACKGROUND_SCREEN),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(all = DIMEN_25),
            verticalArrangement = Arrangement.spacedBy(DIMEN_10)
        ) {
            IntervalInput(
                name = viewModel.highIntervalName,
                intervalTimeInMillis = viewModel.highIntervalTime,
                error = viewModel.highIntervalTimeError
            )

            IntervalInput(
                name = viewModel.lowIntervalName,
                intervalTimeInMillis = viewModel.lowIntervalTime,
                error = viewModel.lowIntervalTimeError
            )
        }

        CreateTimerDetails(viewModel = viewModel)
    }
}

@Composable
private fun CreateTimerDetails(
    viewModel: CreateTimerViewModel
) {
    Column(
        modifier = Modifier
            .background(COLOR_WHITE_100)
            .padding(all = DIMEN_25),
        verticalArrangement = Arrangement.spacedBy(DIMEN_25)
    ) {
        CreateTimerDetailsInputs(viewModel = viewModel)
        CreateTimerDetailsTotalTime(viewModel = viewModel)
        Divider(color = COLOR_BLACK_20, thickness = 1.dp)
        CreateTimerDetailsButtons(viewModel = viewModel)
    }
}

@Composable
private fun CreateTimerDetailsInputs(
    viewModel: CreateTimerViewModel
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(DIMEN_15)
    ) {
        IntervalTextField(
            modifier = Modifier.weight(2f),
            text = viewModel.name,
            placeholder = stringResource(id = R.string.screen_create_timer_name_title),
            error = viewModel.nameError
        )

        IntervalTextField(
            modifier = Modifier.weight(1f),
            text = viewModel.rounds,
            placeholder = stringResource(id = R.string.screen_create_timer_rounds_title),
            includePrefix = true,
            error = viewModel.roundsError
        )
    }
}

@Composable
private fun CreateTimerDetailsTotalTime(
    viewModel: CreateTimerViewModel
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(DIMEN_15)
    ) {
        Spacer(modifier = Modifier.weight(2f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.screen_create_timer_time_title),
                color = COLOR_BLACK_80,
                style = MaterialTheme.typography.labelMedium
            )

            Text(
                text = viewModel.totalTime,
                color = COLOR_BLACK_80,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
private fun CreateTimerDetailsButtons(
    viewModel: CreateTimerViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
        ) {
        IntervalButtonPrimary(
            text = "Delete",
            onClick = {

            }
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(DIMEN_10)
        ) {
            IntervalButtonPrimary(
                text = "Save",
                onClick = {

                }
            )

            IntervalButtonPrimary(
                text = stringResource(id = R.string.screen_create_timer_start_title),
                onClick = {
                    viewModel.onStartClicked()
                }
            )
        }
    }
}

class CreateTimerViewModel(
    private val application: Application,
    private val timerState: TimerStateInterface,
    private val navigator: NavigatorInterface,
): ViewModel() {
    val highIntervalName = application.applicationContext.getString(R.string.screen_create_timer_interval_high_title)
    val lowIntervalName = application.applicationContext.getString(R.string.screen_create_timer_interval_low_title)
    var highIntervalTime: MutableState<Long> = mutableLongStateOf(0)
    var lowIntervalTime: MutableState<Long> = mutableLongStateOf(0)
    var name: MutableState<String> = mutableStateOf("")
    var rounds: MutableState<String> = mutableStateOf("")
    var totalTime by getTotalTime()
    var highIntervalTimeError: MutableState<Boolean> = mutableStateOf(false)
    var lowIntervalTimeError: MutableState<Boolean> = mutableStateOf(false)
    var nameError: MutableState<Boolean> = mutableStateOf(false)
    var roundsError: MutableState<Boolean> = mutableStateOf(false)

    fun onStartClicked() {
        var hasError = false
        if (highIntervalTime.value <= 0) {
            highIntervalTimeError.value = true
            hasError = true
        }

        if (lowIntervalTime.value <= 0) {
            lowIntervalTimeError.value = true
            hasError = true
        }

        if (name.value.isEmpty()) {
            nameError.value = true
            hasError = true
        }

        if (rounds.value.isEmpty()) {
            roundsError.value = true
            hasError = true
        }

        if (hasError) {
            return
        }

        val intervals = mutableListOf<Interval>()
        for (i in 0..rounds.value.toInt()) {
            intervals.add(Interval(name = highIntervalName, durationInMillis = highIntervalTime.value))
            intervals.add(Interval(name = lowIntervalName, durationInMillis = lowIntervalTime.value))
        }
        timerState.setState(IntervalTimer(name.value, intervals))
        navigator.navigate(AppScreen.TIMER_RUN.name)
    }

    private fun getTotalTime(): MutableState<String> {
        val rounds: Long = if (rounds.value.isEmpty()) 0 else rounds.value.toLong()
        val totalTimeInMillis: Long = (highIntervalTime.value + lowIntervalTime.value) * rounds
        val timeAsHumanReadable = TimeUtil.convertTimeAsMillisToHumanReadableString(totalTimeInMillis)
        return mutableStateOf(timeAsHumanReadable)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateTimer() {
    IntervalTheme {
        CreateTimer(viewModel = CreateTimerViewModel(Application(), TimerState(), Navigator()))
    }
}