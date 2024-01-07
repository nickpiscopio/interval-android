package com.plyonest.interval.presentation.constant

import androidx.compose.ui.graphics.Color

// Percentages: https://www.rapidtables.com/convert/number/decimal-to-hex.html
val COLOR_BLACK_0 = Color(0x00000000)
val COLOR_BLACK_20 = Color(0xFFD9D9D9)
val COLOR_BLACK_40 = Color(0x28000000)
val COLOR_BLACK_80 = Color(0x50000000)
val COLOR_BLACK_100 = Color(0xFF000000)
val COLOR_BLUE_100 = Color(0xFF1A6CCC)
val COLOR_WHITE_100 = Color(0xFFFFFFFF)
val COLOR_ERROR = Color(0xFFCC1A1A)
val COLOR_BACKGROUND_SCREEN = Color(0xFFF9F9F9)

class IntervalColor {
    companion object {
        fun getTextFieldColor(hasError: Boolean, hasValue: Boolean): Color {
            if (hasError) {
                return COLOR_ERROR
            }

            if (hasValue) {
                return COLOR_BLACK_100
            }

            return COLOR_BLACK_40
        }
    }
}