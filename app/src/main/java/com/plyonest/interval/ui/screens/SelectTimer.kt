package com.plyonest.interval.ui.screens

import androidx.compose.foundation.background
import com.plyonest.interval.R

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plyonest.interval.constant.COLOR_BACKGROUND_SCREEN
import com.plyonest.interval.constant.COLOR_BLACK_80
import com.plyonest.interval.ui.fragments.IntervalButtonPrimary
import com.plyonest.interval.ui.theme.IntervalTheme

@Composable
fun SelectTimer(
    viewModel: SelectTimerViewModel = viewModel(),
    onCreateTimerClicked: () -> Unit
) {
    if (viewModel.hasTimers()) {
        TimersView(viewModel = viewModel, onCreateTimerClicked = onCreateTimerClicked)
        return
    }

    NoTimersView(viewModel = viewModel, onCreateTimerClicked = onCreateTimerClicked)
}

@Composable
private fun TimersView(
    viewModel: SelectTimerViewModel,
    onCreateTimerClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(COLOR_BACKGROUND_SCREEN)
            .padding(all = dimensionResource(id = R.dimen.dimen_15)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.screen_select_timer_no_timers_title),
            color = COLOR_BLACK_80,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_20))
        )

        IntervalButtonPrimary(
            text = stringResource(id = R.string.screen_select_timer_cta_title),
            onClick = onCreateTimerClicked
        )
    }
}

@Composable
private fun NoTimersView(
    viewModel: SelectTimerViewModel,
    onCreateTimerClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(COLOR_BACKGROUND_SCREEN)
            .padding(all = dimensionResource(id = R.dimen.dimen_15)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_20))
    ) {
        Text(
            text = stringResource(id = R.string.screen_select_timer_no_timers_title),
            color = COLOR_BLACK_80
        )

        IntervalButtonPrimary(
            text = stringResource(id = R.string.screen_select_timer_cta_title),
            onClick = onCreateTimerClicked
        )
    }
}

class SelectTimerViewModel: ViewModel() {
    fun hasTimers(): Boolean {
        return true
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectTimer() {
    IntervalTheme {
        SelectTimer { }
    }
}