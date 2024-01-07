package com.plyonest.interval.presentation.ui.fragments

import android.annotation.SuppressLint
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plyonest.interval.presentation.constant.COLOR_BLACK_100
import com.plyonest.interval.presentation.constant.COLOR_BLACK_40
import com.plyonest.interval.presentation.constant.COLOR_ERROR
import com.plyonest.interval.presentation.constant.CORNER_RADIUS_TEXT_FIELD
import com.plyonest.interval.presentation.constant.IntervalColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntervalTextField(
    modifier: Modifier,
    text: MutableState<String>,
    placeholder: String,
    includePrefix: Boolean = false,
    error: MutableState<Boolean>
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
            isError = error.value,
            interactionSource = interactionSource,
            visualTransformation = VisualTransformation.None,
            label = {
                Text(
                    text = placeholder,
                )
            },
            leadingIcon = if (includePrefix) {
                {
                    Text(text = "x", color = IntervalColor.getTextFieldColor(hasError = error.value, hasValue = text.value.isNotEmpty()))
                }
            } else null,
            container = {
                OutlinedTextFieldDefaults.ContainerBox(
                    enabled = true,
                    isError = error.value,
                    interactionSource = interactionSource,
                    colors = OutlinedTextFieldDefaults.colors(
                        errorBorderColor = COLOR_ERROR,
                        errorTextColor = COLOR_ERROR,
                        errorLeadingIconColor = COLOR_ERROR,
                        unfocusedLeadingIconColor = COLOR_BLACK_40,
                        focusedBorderColor = COLOR_BLACK_100,
                        unfocusedBorderColor = COLOR_BLACK_40,
                        unfocusedLabelColor = COLOR_BLACK_40
                    ),
                    shape = RoundedCornerShape(CORNER_RADIUS_TEXT_FIELD),
                    focusedBorderThickness = 2.dp,
                    unfocusedBorderThickness = 2.dp
                )
            }
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewIntervalTextField() {
    IntervalTextField(
        modifier = Modifier,
        text = mutableStateOf(""),
        placeholder = "Name",
        error = mutableStateOf(false)
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewIntervalTextFieldWithText() {
    IntervalTextField(
        modifier = Modifier,
        text = mutableStateOf("Name"),
        placeholder = "Name",
        error = mutableStateOf(false)
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewIntervalTextFieldWithError() {
    IntervalTextField(
        modifier = Modifier,
        text = mutableStateOf(""),
        placeholder = "Name",
        error = mutableStateOf(true)
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewIntervalTextFieldWithErrorWithText() {
    IntervalTextField(
        modifier = Modifier,
        text = mutableStateOf("Name"),
        placeholder = "Name",
        error = mutableStateOf(true)
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewIntervalTextFieldWithPrefix() {
    IntervalTextField(
        modifier = Modifier,
        text = mutableStateOf(""),
        placeholder = "Rounds",
        includePrefix = true,
        error = mutableStateOf(false)
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewIntervalTextFieldWithPrefixAndError() {
    IntervalTextField(
        modifier = Modifier,
        text = mutableStateOf(""),
        placeholder = "Rounds",
        includePrefix = true,
        error = mutableStateOf(true)
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewIntervalTextFieldWithPrefixAndText() {
    IntervalTextField(
        modifier = Modifier,
        text = mutableStateOf("Rounds"),
        placeholder = "Rounds",
        includePrefix = true,
        error = mutableStateOf(false)
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewIntervalTextFieldWithPrefixAndTextAndError() {
    IntervalTextField(
        modifier = Modifier,
        text = mutableStateOf("Rounds"),
        placeholder = "Rounds",
        includePrefix = true,
        error = mutableStateOf(true)
    )
}