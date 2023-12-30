package com.plyonest.interval.ui.fragments

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.plyonest.interval.constant.COLOR_BLACK_100
import com.plyonest.interval.constant.COLOR_WHITE_100
import com.plyonest.interval.constant.CORNER_RADIUS_TEXT_FIELD
import com.plyonest.interval.constant.DIMEN_15
import com.plyonest.interval.constant.ELEVATION_SHADOW
import com.plyonest.interval.utils.TimeUtil

@Composable
fun IntervalInput(
    placeholder: String,
    intervalTimeInMillis: MutableState<Long>,
) {
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
            Text(text = placeholder)

            Box(
                modifier = Modifier
                    .padding(all = DIMEN_15),
                contentAlignment = Alignment.BottomEnd
            ) {

                Text(text = TimeUtil.convertMillisToHumanReadableTime(intervalTimeInMillis.value, true))

                BasicTextField(
                    value = TimeUtil.convertMillisToHumanReadableTime(intervalTimeInMillis.value),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    cursorBrush = SolidColor(COLOR_BLACK_100),
                    onValueChange = { newIntervalTime ->
                        intervalTimeInMillis.value =
                            TimeUtil.convertHumanReadableTimeToMillis(newIntervalTime)
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
        intervalTimeInMillis = mutableLongStateOf(5039),
        placeholder = "High Interval",
    )
}