package com.streamer272.hell.ui.login

import com.streamer272.hell.api.UserLogin
import com.streamer272.hell.api.BACKEND_URL
import com.streamer272.hell.api.client
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

suspend fun login(username: String, password: String): HttpResponse {
    val userLogin = UserLogin(username, password)

    return client.request("$BACKEND_URL/login") {
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(userLogin)
    }
}
