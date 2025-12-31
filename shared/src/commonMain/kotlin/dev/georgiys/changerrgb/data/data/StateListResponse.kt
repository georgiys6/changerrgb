package dev.georgiys.changerrgb.data.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class StateListResponse(
    val status: String,
    val response: List<JsonElement>
)