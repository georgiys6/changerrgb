package dev.georgiys.changerrgb.data.data.list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CO2ppmItemList(
    @SerialName("Time")
    val time: String,
    @SerialName("CO2ppm")
    val cO2ppm: Double
)