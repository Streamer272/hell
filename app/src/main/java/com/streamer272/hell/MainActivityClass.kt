package com.streamer272.hell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ContentView
import androidx.compose.runtime.*
import com.streamer272.hell.ui.dashboard.DashboardActivity
import com.streamer272.hell.ui.login.LoginActivity
import com.streamer272.hell.ui.theme.HellTheme

class MainActivityClass : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivity()
        }
    }
}

@Composable
fun MainActivity() {
    var state by remember { mutableStateOf(AppState.LOGIN) }

    fun changeState(newState: AppState) {
        state = newState
    }

    HellTheme {
        if (state == AppState.LOGIN) {
            LoginActivity(changeState = ::changeState)
        }
        else if (state == AppState.DASHBOARD) {
            DashboardActivity(changeState = ::changeState)
        }
    }
}
