package com.streamer272.hell.api.auth

import com.streamer272.hell.api.UserAuth
import com.streamer272.hell.api.BACKEND_URL
import com.streamer272.hell.api.client
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

suspend fun login(username: String, password: String): HttpResponse {
    val auth = UserAuth(username, password)

    return client.request("$BACKEND_URL/auth/login") {
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(auth)
    }
}

suspend fun register(username: String, password: String): HttpResponse {
    val auth = UserAuth(username, password)

    return client.request("$BACKEND_URL/auth/register") {
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(auth)
    }
}
