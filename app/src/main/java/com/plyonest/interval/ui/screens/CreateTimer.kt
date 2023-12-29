package com.plyonest.interval.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.plyonest.interval.AppScreen
import com.plyonest.interval.constant.COLOR_BACKGROUND_SCREEN
import com.plyonest.interval.constant.COLOR_BLACK_100
import com.plyonest.interval.constant.COLOR_BLACK_20
import com.plyonest.interval.constant.COLOR_BLACK_80
import com.plyonest.interval.constant.COLOR_WHITE_100
import com.plyonest.interval.constant.CORNER_RADIUS_TEXT_FIELD
import com.plyonest.interval.constant.DIMEN_10
import com.plyonest.interval.constant.DIMEN_15
import com.plyonest.interval.constant.DIMEN_20
import com.plyonest.interval.constant.DIMEN_25
import com.plyonest.interval.ui.fragments.IntervalButtonPrimary
import com.plyonest.interval.ui.fragments.IntervalTextField
import com.plyonest.interval.ui.theme.IntervalTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun CreateTimer(
    viewModel: CreateTimerViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(COLOR_BACKGROUND_SCREEN),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.screen_select_timer_no_timers_title),
            color = COLOR_BLACK_80
        )

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
    private var navController: NavHostController
): ViewModel() {
    var name: MutableState<String> = mutableStateOf("")
    var rounds: MutableState<String> = mutableStateOf("")
    var totalTime by mutableStateOf("0s")

    fun onStartClicked() {
        Log.d("Interval", name.value)
        Log.d("Interval", rounds.value)
        navController.navigate(AppScreen.TIMER_RUN.name)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateTimer() {
    IntervalTheme {
        CreateTimer(viewModel = CreateTimerViewModel(rememberNavController()))
    }
}