package dev.georgiys.changerrgb.data.data.list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TemperatureItemList(
    @SerialName("Time")
    val time: String,
    @SerialName("Temperature")
    val temperature: Double
)