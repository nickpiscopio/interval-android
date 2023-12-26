package com.plyonest.interval.ui.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.plyonest.interval.R

@Composable
fun IntervalButton(text: String, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        onClick = onClick
    ) {
        Box(modifier = Modifier
            .background(Brush.linearGradient(colors = listOf(
                Color.Blue,
                Color.Green
            )))
            .shadow(elevation = dimensionResource(id = R.dimen.elevation_2))
            .clip(shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius), dimensionResource(
                R.dimen.corner_radius), dimensionResource(R.dimen.corner_radius), dimensionResource(
                R.dimen.corner_radius))),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = text,
                color = Color.White,
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(id = R.dimen.dimen_14),
                        horizontal = dimensionResource(id = R.dimen.dimen_24)
                    )

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    IntervalButton(text = "Hello") { }
}