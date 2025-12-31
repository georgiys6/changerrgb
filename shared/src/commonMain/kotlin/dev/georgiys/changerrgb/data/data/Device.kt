package dev.georgiys.changerrgb.data.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Device(
    @SerialName("ChipId")
    val chipId: Long,
    @SerialName("TypeDevice")
    val typeDevice: String,
    @SerialName("DeviceName")
    val deviceName: String
)
