package dev.georgiys.changerrgb.data.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class StateResponse (
    val status: String,
    @SerialName("response")
    val response: JsonElement
)