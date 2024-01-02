package com.plyonest.interval.di

import com.plyonest.interval.data.Navigator
import com.plyonest.interval.data.TimerState
import com.plyonest.interval.domain.interfaces.NavigatorInterface
import com.plyonest.interval.domain.interfaces.TimerStateInterface
import com.plyonest.interval.presentation.ui.screens.CreateTimerViewModel
import com.plyonest.interval.presentation.ui.screens.RunTimerViewModel
import com.plyonest.interval.presentation.ui.screens.SelectTimerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<NavigatorInterface> { Navigator() }
    single<TimerStateInterface> { TimerState() }

    viewModel { SelectTimerViewModel(get()) }
    viewModel { CreateTimerViewModel(get(), get()) }
    viewModel { RunTimerViewModel(get()) }
}