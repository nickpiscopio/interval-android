package com.plyonest.interval.ui.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.plyonest.interval.constant.DIMEN_25
import com.plyonest.interval.ui.theme.IntervalTheme

@Composable
fun RunTimer(
    viewModel: RunTimerViewModel
) {
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
    }
}

class RunTimerViewModel(
    private var navController: NavHostController
): ViewModel() {

}

@Preview(showBackground = true)
@Composable
fun PreviewRunTimer() {
    IntervalTheme {
        RunTimer(RunTimerViewModel(rememberNavController()))
    }
}