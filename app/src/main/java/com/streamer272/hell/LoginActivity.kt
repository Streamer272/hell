package com.streamer272.hell

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun LoginActivity(changeState: (AppState) -> Unit) {
    var username by remember { mutableStateOf("") }

    Box {
        Text(text = "Log in", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontSize = 40.sp)
        TextField(
            value = "",
            onValueChange = fun (newUsername: String) {
                username = newUsername
            },
            label = { Text(text = "Username") },
        )
    }
}
