package com.plyonest.interval.presentation.ui.screens

import android.util.Log
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
import com.plyonest.interval.domain.interfaces.NavigatorInterface
import com.plyonest.interval.domain.interfaces.TimerStateInterface
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
                name = stringResource(id = R.string.screen_create_timer_interval_high_title),
                intervalTimeInMillis = viewModel.highIntervalTime
            )

            IntervalInput(
                name = stringResource(id = R.string.screen_create_timer_interval_low_title),
                intervalTimeInMillis = viewModel.lowIntervalTime
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
            placeholder = stringResource(id = R.string.screen_create_timer_name_title)
        )

        IntervalTextField(
            modifier = Modifier.weight(1f),
            text = viewModel.rounds,
            placeholder = stringResource(id = R.string.screen_create_timer_rounds_title),
            includePrefix = true
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
                color = COLOR_BLACK_80
            )

            Text(
                text = viewModel.totalTime,
                color = COLOR_BLACK_80
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
    private val timerState: TimerStateInterface,
    private val navigator: NavigatorInterface,
): ViewModel() {
    var highIntervalTime: MutableState<Long> = mutableLongStateOf(0)
    var lowIntervalTime: MutableState<Long> = mutableLongStateOf(0)
    var name: MutableState<String> = mutableStateOf("")
    var rounds: MutableState<String> = mutableStateOf("")
    var totalTime by mutableStateOf("0s")

    fun onStartClicked() {
        Log.d("Interval", name.value)
        Log.d("Interval", rounds.value)
        timerState.setState(
            IntervalTimer(name.value, rounds.value.toInt(), highIntervalTime.value, lowIntervalTime.value))
        navigator.navigate(AppScreen.TIMER_RUN.name)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateTimer() {
    IntervalTheme {
        CreateTimer(viewModel = koinViewModel())
    }
}