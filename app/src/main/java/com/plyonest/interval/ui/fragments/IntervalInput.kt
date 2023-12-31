package com.plyonest.interval.ui.fragments

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.plyonest.interval.constant.COLOR_BLACK_100
import com.plyonest.interval.constant.COLOR_WHITE_100
import com.plyonest.interval.constant.CORNER_RADIUS_TEXT_FIELD
import com.plyonest.interval.constant.DIMEN_1
import com.plyonest.interval.constant.DIMEN_15
import com.plyonest.interval.constant.DIMEN_2
import com.plyonest.interval.constant.ELEVATION_SHADOW
import com.plyonest.interval.utils.HoursMinutesSeconds
import com.plyonest.interval.utils.TimeUtil

@Composable
fun AutoSizableTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            cursorBrush = SolidColor(COLOR_BLACK_100),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun IntervalInput(
    name: String,
    intervalTimeInMillis: MutableState<Long>,
) {
    val hms = remember { mutableStateOf(HoursMinutesSeconds("00", "00", "00")) }
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
                modifier = Modifier
                    .weight(1f),
                text = name
            )

            Row(
                modifier = Modifier
                    .weight(1f)
            ){
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = DIMEN_2),
                    horizontalArrangement = Arrangement.spacedBy(DIMEN_1)
                ) {
                    Text(text = hms.value.getHours())
                    Text(text = "h")
                }

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = DIMEN_2),
                    horizontalArrangement = Arrangement.spacedBy(DIMEN_1)
                ) {
                    Text(text = hms.value.getMinutes())
                    Text(text = "m")
                }

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = DIMEN_2),
                    horizontalArrangement = Arrangement.spacedBy(DIMEN_1)
                ) {
                    AutoSizableTextField(
                        modifier = Modifier
                            .weight(1f),
                        value = hms.value.getSeconds(),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = { newSeconds ->
                            val newMillis = TimeUtil.convertHoursMinutesSecondsToMillis(
                                hms.value.getHours(),
                                hms.value.getMinutes(),
                                newSeconds
                            )
                            intervalTimeInMillis.value = newMillis
                            hms.value = TimeUtil.convertMillisToHoursMinutesSeconds(newMillis)
                        }
                    )

                    Text(text = "s", modifier = Modifier
                        .weight(1f),)
                }
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
    )
}