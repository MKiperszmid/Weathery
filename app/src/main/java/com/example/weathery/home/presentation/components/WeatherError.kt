package com.example.weathery.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sync
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun WeatherError(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = onRetry) {
            Icon(
                imageVector = Icons.Default.Sync,
                contentDescription = "retry",
                tint = Color.White
            )
        }
        Text(
            text = "Oops. There was an error. Please retry.", color = Color.White,
            fontSize = 12.sp
        )
    }
}