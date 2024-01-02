package com.plyonest.interval.presentation.ui.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.plyonest.interval.presentation.constant.COLOR_BLACK_40
import com.plyonest.interval.presentation.constant.COLOR_BLUE_100
import com.plyonest.interval.presentation.constant.CORNER_RADIUS_BUTTON
import com.plyonest.interval.presentation.constant.DIMEN_15
import com.plyonest.interval.presentation.constant.DIMEN_25
import com.plyonest.interval.presentation.constant.ELEVATION_SHADOW

@Composable
fun IntervalButtonPrimary(
    text: String,
    onClick: () -> Unit
) {
    IntervalButton(
        text = text,
        textColor = Color.White,
        buttonColor = COLOR_BLUE_100,
        onClick = onClick
    )
}

@Composable
fun IntervalButton(
    text: String,
    textColor: Color,
    buttonColor: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.shadow(ELEVATION_SHADOW, RoundedCornerShape(CORNER_RADIUS_BUTTON)),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        contentPadding = PaddingValues(),
    onClick = { onClick() })
    {
        Box(
            modifier = Modifier
                .background(Brush.linearGradient(colors = listOf(
                    COLOR_BLUE_100,
                    COLOR_BLACK_40
                )))
                .padding(
                    horizontal = DIMEN_25,
                    vertical = DIMEN_15
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = textColor, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIntervalButtonPrimary() {
    IntervalButton(text = "Hello", textColor = Color.White, buttonColor = COLOR_BLUE_100) { }
}

@Preview(showBackground = true)
@Composable
fun PreviewIntervalButton() {
    IntervalButton(text = "Hello", textColor = Color.White, buttonColor = COLOR_BLUE_100) { }
}