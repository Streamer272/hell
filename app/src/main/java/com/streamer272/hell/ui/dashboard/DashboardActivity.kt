package com.streamer272.hell.ui.dashboard

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.streamer272.hell.AppState

@Composable
fun DashboardActivity(changeState: (AppState) -> Unit) {
    Text(text = "Hello World")
}
