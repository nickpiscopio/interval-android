package com.plyonest.interval.presentation.ui.fragments

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.plyonest.interval.presentation.constant.COLOR_BLACK_100
import com.plyonest.interval.presentation.constant.COLOR_BLACK_40
import com.plyonest.interval.presentation.constant.COLOR_ERROR
import com.plyonest.interval.presentation.constant.COLOR_WHITE_100
import com.plyonest.interval.presentation.constant.CORNER_RADIUS_TEXT_FIELD
import com.plyonest.interval.presentation.constant.DIMEN_1
import com.plyonest.interval.presentation.constant.DIMEN_15
import com.plyonest.interval.presentation.constant.DIMEN_2
import com.plyonest.interval.presentation.constant.ELEVATION_SHADOW
import com.plyonest.interval.presentation.constant.IntervalColor
import com.plyonest.interval.presentation.constant.IntervalColor.Companion.getTextFieldColor
import com.plyonest.interval.presentation.utils.HoursMinutesSeconds
import com.plyonest.interval.presentation.utils.TimeUtil

@Composable
fun IntervalInput(
    name: String,
    intervalTimeInMillis: MutableState<Long>,
    error: MutableState<Boolean>
) {
    val hms = remember { mutableStateOf(HoursMinutesSeconds("00", "00", "00")) }
    val timeAsString = remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val pattern = remember { Regex("^\\d+\$") }

    Card(
        modifier = Modifier
            .background(COLOR_WHITE_100)
            .shadow(ELEVATION_SHADOW, RoundedCornerShape(CORNER_RADIUS_TEXT_FIELD)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = DIMEN_15),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.labelMedium
            )

            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .clickable(onClick = {
                            focusRequester.requestFocus()
                        })
                        .align(Alignment.BottomEnd),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = DIMEN_2),
                        horizontalArrangement = Arrangement.spacedBy(DIMEN_1)
                    ) {
                        Text(
                            text = hms.value.getHours(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = getTextFieldColor(error.value, !hms.value.areHoursEmpty())
                        )
                        Text(
                            text = "h",
                            style = MaterialTheme.typography.bodyMedium,
                            color = getTextFieldColor(error.value, !hms.value.areHoursEmpty())
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(horizontal = DIMEN_2),
                        horizontalArrangement = Arrangement.spacedBy(DIMEN_1),
                    ) {
                        Text(
                            text = hms.value.getMinutes(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = getTextFieldColor(error.value, !hms.value.areMinutesEmpty())
                        )
                        Text(
                            text = "m",
                            style = MaterialTheme.typography.bodyMedium,
                            color = getTextFieldColor(error.value, !hms.value.areMinutesEmpty())
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(horizontal = DIMEN_2),
                        horizontalArrangement = Arrangement.spacedBy(DIMEN_1)
                    ) {
                        Text(
                            text = hms.value.getSeconds(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = getTextFieldColor(error.value, !hms.value.areSecondsEmpty())
                        )
                        Text(
                            text = "s",
                            style = MaterialTheme.typography.bodyMedium,
                            color = getTextFieldColor(error.value, !hms.value.areSecondsEmpty())
                        )
                    }
                }

                BasicTextField(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .alpha(0f),
                    value = timeAsString.value,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = { newIntervalTime: String ->
                        if (newIntervalTime.isEmpty() || newIntervalTime.matches(pattern)) {
                            timeAsString.value = newIntervalTime
                            hms.value = TimeUtil.convertTimeAsStringToHms(timeAsString.value)
                            intervalTimeInMillis.value =
                                TimeUtil.convertHoursMinutesSecondsToMillis(hms.value)
                        }
                    }
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewIntervalInput() {
    IntervalInput(
        name = "High Interval",
        intervalTimeInMillis = mutableLongStateOf(5039),
        error = mutableStateOf(false)
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewIntervalInputWithError() {
    IntervalInput(
        name = "High Interval",
        intervalTimeInMillis = mutableLongStateOf(5039),
        error = mutableStateOf(true)
    )
}