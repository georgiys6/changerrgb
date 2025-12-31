package dev.georgiys.changerrgb.data.data

import kotlinx.serialization.Serializable

@Serializable
data class Authorisation(
    val url: String = "",
    val login: String,
    val password: String
)
