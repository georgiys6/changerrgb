package dev.georgiys.changerrgb.data.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataTelemetry(
    @SerialName("Temperature")
    val temperature: Double = 0.0,
    @SerialName("Humidity")
    val humidity: Double = 0.0,
    @SerialName("CO2ppm")
    val co2ppm: Int = -1
)
