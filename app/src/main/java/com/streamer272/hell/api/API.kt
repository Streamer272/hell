package com.streamer272.hell.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

val client = HttpClient(CIO) {
    install(Logging) {
        level = LogLevel.ALL
    }
    install(ContentNegotiation) {
        json()
    }
}

suspend fun makeRequest(uri: String, httpMethod: HttpMethod?, body: String?): HttpResponse {
    return client.request("http://localhost:8000$uri") {
        method = httpMethod ?: HttpMethod.Get
        body?.let { setBody(body) }
    }
}
