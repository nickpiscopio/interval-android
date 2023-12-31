package com.plyonest.interval.presentation.ui.screens

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.plyonest.interval.data.Navigator
import com.plyonest.interval.domain.interfaces.NavigatorInterface
import com.plyonest.interval.presentation.AppScreen
import com.plyonest.interval.presentation.constant.COLOR_BACKGROUND_SCREEN
import com.plyonest.interval.presentation.constant.COLOR_BLACK_80
import com.plyonest.interval.presentation.constant.DIMEN_15
import com.plyonest.interval.presentation.constant.DIMEN_20
import com.plyonest.interval.presentation.ui.fragments.IntervalButtonPrimary
import com.plyonest.interval.presentation.ui.theme.IntervalTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SelectTimer(
    viewModel: SelectTimerViewModel = koinViewModel()
) {
    if (viewModel.hasTimers()) {
        TimersView(viewModel = viewModel)

        return
    }

    NoTimersView(viewModel = viewModel)
}

@Composable
private fun TimersView(
    viewModel: SelectTimerViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(COLOR_BACKGROUND_SCREEN)
            .padding(all = DIMEN_15),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.screen_select_timer_no_timers_title),
            color = COLOR_BLACK_80,
            modifier = Modifier.padding(bottom = DIMEN_20)
        )

        IntervalButtonPrimary(
            text = stringResource(id = R.string.screen_select_timer_cta_title),
            onClick = {
                viewModel.onCreateTimerClicked()
            }
        )
    }
}

@Composable
private fun NoTimersView(
    viewModel: SelectTimerViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(COLOR_BACKGROUND_SCREEN)
            .padding(all = DIMEN_15),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(DIMEN_20)
    ) {
        Text(
            text = stringResource(id = R.string.screen_select_timer_no_timers_title),
            color = COLOR_BLACK_80
        )

        IntervalButtonPrimary(
            text = stringResource(id = R.string.screen_select_timer_cta_title),
            onClick = {
                viewModel.onCreateTimerClicked()
            }
        )
    }
}

class SelectTimerViewModel(
    private val navigator: NavigatorInterface,
): ViewModel() {
    fun onCreateTimerClicked() {
        navigator.navigate(AppScreen.TIMER_CREATE.name)
    }
    fun hasTimers(): Boolean {
        return true
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectTimer() {
    IntervalTheme {
        SelectTimer(SelectTimerViewModel(Navigator()))
    }
}