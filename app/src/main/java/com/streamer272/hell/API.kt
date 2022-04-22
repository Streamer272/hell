package com.streamer272.hell

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

val client = HttpClient()

suspend fun makeRequest(uri: String, httpMethod: HttpMethod?, body: Any?): HttpResponse {
    return client.request("http://localhost:8000$uri") {
        method = httpMethod ?: HttpMethod.Get
        body?.let { setBody(body) }
    }
}
