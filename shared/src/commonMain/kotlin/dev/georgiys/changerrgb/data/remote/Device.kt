package dev.georgiys.changerrgb.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Device(
//    @SerialName("UID")
//    val uuid: Int,
//    val type: String,
//    val temperature: Double? = null,
//    val humidity: Double? = null,
//    @SerialName("IsSensor")
//    val isSensor: Boolean? = null,
//    @SerialName("CO2ppm")
//    val co2ppm: Int? = null,
    @SerialName("ChipId")
    val chipId: Long,
    @SerialName("TypeDevice")
    val typeDevice: String,
    @SerialName("DeviceName")
    val deviceName: String
)
