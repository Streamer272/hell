package com.streamer272.hell.api

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val id: Int,
    val username: String,
)

@Serializable
data class UserLogin (
    val username: String,
    val password: String,
)
