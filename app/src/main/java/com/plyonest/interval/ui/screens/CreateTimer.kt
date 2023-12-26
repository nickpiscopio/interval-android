package com.plyonest.interval.ui.screens

import com.plyonest.interval.R

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.plyonest.interval.ui.fragments.IntervalButton
import com.plyonest.interval.ui.theme.IntervalTheme

@Composable
fun CreateTimer(onStartClicked: () -> Unit) {
    Column(
        modifier = Modifier.padding(all = dimensionResource(id = R.dimen.dimen_14)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.screen_select_timer_no_timers_title)
        )

        IntervalButton(
            text = stringResource(id = R.string.screen_create_timer_start_title),
            onClick = onStartClicked
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateTimer() {
    IntervalTheme {
        CreateTimer { }
    }
}