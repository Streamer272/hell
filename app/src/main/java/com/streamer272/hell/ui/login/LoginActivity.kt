package com.streamer272.hell.ui.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.streamer272.hell.AppState
import com.streamer272.hell.R
import com.streamer272.hell.api.auth.login
import com.streamer272.hell.api.auth.register
import com.streamer272.hell.ui.theme.LoginBox
import com.streamer272.hell.ui.theme.LoginInput
import io.ktor.http.*
import kotlinx.coroutines.launch

@Composable
fun LoginActivity(changeState: (AppState) -> Unit) {
    val context = LocalContext.current
    val composableScope = rememberCoroutineScope()
    val toastUsernameMissing = stringResource(R.string.toast_username_missing)
    val toastPasswordMissing = stringResource(R.string.toast_password_missing)
    val toastPasswordShort = stringResource(R.string.toast_password_short)
    val toastLoginSuccessful = stringResource(R.string.toast_login_successful)
    val toastLoginFailed = stringResource(R.string.toast_login_failed)
    val toastCredsInvalid = stringResource(R.string.toast_creds_invalid)
    val toastError = stringResource(R.string.toast_error)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    fun login() {
        when {
            username == "" -> {
                return Toast.makeText(context, toastUsernameMissing, Toast.LENGTH_SHORT).show()
            }
            password == "" -> {
                return Toast.makeText(context, toastPasswordMissing, Toast.LENGTH_SHORT).show()
            }
            else -> composableScope.launch {
                try {
                    val result = login(username, password)
                    when (result.status) {
                        HttpStatusCode.OK -> {
                            Toast.makeText(context, toastLoginSuccessful, Toast.LENGTH_SHORT).show()
                            changeState(AppState.DASHBOARD)
                        }
                        HttpStatusCode.Unauthorized -> {
                            Toast.makeText(context, toastCredsInvalid, Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(context, toastLoginFailed, Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        toastError.replace("\$ERR", "${e.message}"),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun register() {
        when {
            username == "" -> {
                return Toast.makeText(context, toastUsernameMissing, Toast.LENGTH_SHORT).show()
            }
            password == "" -> {
                return Toast.makeText(context, toastPasswordMissing, Toast.LENGTH_SHORT).show()
            }
            password.length < 5 -> {
                return Toast.makeText(context, toastPasswordShort, Toast.LENGTH_SHORT).show()
            }
            else -> composableScope.launch {
                try {
                    val result = register(username, password)
                    when (result.status) {
                        HttpStatusCode.OK -> {
                            Toast.makeText(context, toastLoginSuccessful, Toast.LENGTH_SHORT).show()
                            changeState(AppState.DASHBOARD)
                        }
                        HttpStatusCode.Unauthorized -> {
                            Toast.makeText(context, toastCredsInvalid, Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(context, toastLoginFailed, Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        toastError.replace("\$ERR", "${e.message}"),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.text_log_in),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 40.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.clip(RoundedCornerShape(10.dp))) {
            Column(
                modifier = Modifier
                    .background(LoginBox)
                    .padding(16.dp)
            ) {
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(text = stringResource(R.string.text_username)) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .background(LoginInput)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    label = { Text(text = stringResource(R.string.text_password)) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .background(LoginInput)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { login() },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = stringResource(R.string.text_log_in), fontSize = 18.sp)
                }
            }
        }
    }
}
