package com.streamer272.hell.api

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val username: String,
)
