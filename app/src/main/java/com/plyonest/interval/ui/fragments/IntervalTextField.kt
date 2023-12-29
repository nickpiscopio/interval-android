package com.plyonest.interval.ui.fragments

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plyonest.interval.constant.COLOR_BLACK_100
import com.plyonest.interval.constant.CORNER_RADIUS_TEXT_FIELD

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntervalTextField(
    modifier: Modifier,
    text: MutableState<String>,
    placeholder: String,
    includePrefix: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        modifier = modifier,
        textStyle = LocalTextStyle.current.copy(textAlign = if (includePrefix) TextAlign.End else TextAlign.Start),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = if (includePrefix) KeyboardType.Number else KeyboardType.Text),
        value = text.value,
        singleLine = true,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(COLOR_BLACK_100),
        onValueChange = { newText -> text.value = newText }

    ) { innerTextField ->
        OutlinedTextFieldDefaults.DecorationBox(
            value = text.value,
            innerTextField = innerTextField,
            enabled = true,
            singleLine = true,
            interactionSource = interactionSource,
            visualTransformation = VisualTransformation.None,
            label = {
                Text(
                    text = placeholder,
                )
            },
            leadingIcon = if (includePrefix) {
                {
                    Text("x")
                }
            } else null,
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    enabled = true,
                    isError = false,
                    interactionSource = interactionSource,
                    colors = OutlinedTextFieldDefaults.colors(),
                    shape = RoundedCornerShape(CORNER_RADIUS_TEXT_FIELD),
                    focusedBorderThickness = 2.dp,
                    unfocusedBorderThickness = 2.dp
                )
            }
        )
    }
}

