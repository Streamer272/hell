package com.streamer272.hell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.streamer272.hell.ui.theme.HellTheme

class MainActivity : ComponentActivity() {
    private var state: AppState = AppState.LOGIN

    private fun changeState(newState: AppState) {
        state = newState
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HellTheme {
                if (state == AppState.LOGIN) {
                    LoginActivity(changeState = ::changeState)
                }
                else if (state == AppState.DASHBOARD) {
                    DashboardActivity(changeState = ::changeState)
                }
            }
        }
    }
}
