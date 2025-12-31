package dev.georgiys.changerrgb.android.itemchip.telemetry

import kotlinx.serialization.json.JsonElement

data class AxisScreenState(
    val loading: Boolean = false,
    val points: List<JsonElement> = emptyList(),
    val typeDevice: String,
    val errorMessage: String? = null
)
