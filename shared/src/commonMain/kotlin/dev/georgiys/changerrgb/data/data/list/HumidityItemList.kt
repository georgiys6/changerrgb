package dev.georgiys.changerrgb.data.data.list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HumidityItemList(
    @SerialName("Time")
    val time: String,
    @SerialName("Humidity")
    val humidity: Double
)
