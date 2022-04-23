package com.streamer272.hell.ui.login

import com.streamer272.hell.api.makeRequest
import io.ktor.client.statement.*
import io.ktor.http.*

suspend fun login(username: String, password: String): HttpResponse {
    return makeRequest("/login", HttpMethod.Post, mapOf("username" to username, "password" to password))
}
