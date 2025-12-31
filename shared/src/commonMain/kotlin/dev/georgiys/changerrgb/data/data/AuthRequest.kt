package dev.georgiys.changerrgb.data.data

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val token: String = "",
    val status: String = "",
    val message: String = "",
    val error: String = ""
)
