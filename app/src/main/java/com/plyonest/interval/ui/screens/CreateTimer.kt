package com.plyonest.interval.ui.screens

import androidx.compose.foundation.background
import com.plyonest.interval.R

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.plyonest.interval.constant.COLOR_BLACK_20
import com.plyonest.interval.constant.COLOR_BLACK_80
import com.plyonest.interval.constant.COLOR_WHITE_100
import com.plyonest.interval.ui.fragments.IntervalButtonPrimary
import com.plyonest.interval.ui.theme.IntervalTheme

@Composable
fun CreateTimer(
    viewModel: CreateTimerViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(COLOR_BACKGROUND_SCREEN),
//            .padding(all = dimensionResource(id = R.dimen.dimen_14)),
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
            .padding(all = dimensionResource(id = R.dimen.dimen_15)),
    ) {
        CreateTimerDetailsInputs(viewModel = viewModel)
        Divider(color = COLOR_BLACK_20, thickness = 1.dp)
        CreateTimerDetailsButtons(viewModel = viewModel)
    }
}

@Composable
private fun CreateTimerDetailsInputs(
    viewModel: CreateTimerViewModel
) {
    Column(
        modifier = Modifier
            .padding(all = dimensionResource(id = R.dimen.dimen_15)),
    ) {

        Row() {
            OutlinedTextField(
                value = viewModel.name,
                onValueChange = { viewModel.name = it },
                label = { Text("Name") }
            )

            OutlinedTextField(
                value = viewModel.rounds,
                onValueChange = { viewModel.rounds = it },
                label = { Text("Rounds") }
            )
        }
    }
}

@Composable
private fun CreateTimerDetailsButtons(
    viewModel: CreateTimerViewModel
) {
    Row {
        IntervalButtonPrimary(
            text = "Delete",
            onClick = {

            }
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
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
    var name by mutableStateOf("")
    var rounds by mutableStateOf("")

    fun onStartClicked() {
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