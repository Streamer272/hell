package com.streamer272.hell.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.streamer272.hell.AppState

@Composable
fun LoginActivity(changeState: (AppState) -> Unit) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val login = fun() {
        var message = ""
        when {
            username == "" -> {
                message = "Username is required"
            }
            password == "" -> {
                message = "Password is required"
            }
            else -> {
    //            val result = login(username, password)
    //            if (result.status == HttpStatusCode.OK) {
    //                message = "Login successful"
    //                changeState(AppState.DASHBOARD)
    //            }
    //            else {
    //                message = "Login failed"
    //            }
                message = "Login successful"
                changeState(AppState.DASHBOARD)
            }
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Log in", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontSize = 40.sp)

        Column {
            TextField(
                value = username,
                onValueChange = fun (newUsername: String) {
                    username = newUsername
                },
                label = { Text(text = "Username") },
                modifier = Modifier
                    .padding(top = 32.dp)
                    .align(Alignment.CenterHorizontally)
            )
            TextField(
                value = password,
                onValueChange = fun (newPassword: String) {
                    password = newPassword
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = "Password") },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = login,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.End)
            ) {
                Text(text = "Log in", fontSize = 18.sp)
            }
        }
    }
}
