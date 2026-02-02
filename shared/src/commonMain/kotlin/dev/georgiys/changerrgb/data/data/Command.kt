package dev.georgiys.changerrgb.data.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Command(
    @SerialName("ChipId")
    val chipId: Long? = null,
    val login: String = "",
    val password: String = "",
    @SerialName("TypeMesseage")
    val typeMesseage: String,
    @SerialName("Token")
    val token: String = "",
    @SerialName("Mode")
    val mode: Int = -1,
    @SerialName("ColorR")
    val colorR: Int = -1,
    @SerialName("ColorG")
    val colorG: Int = -1,
    @SerialName("ColorB")
    val colorB: Int = -1,
    @SerialName("Speed")
    val speed: Int? = -8,
    @SerialName("Brightness")
    val brightness: Int? = -9,
    @SerialName("SensorType")
    val sensorType: String? = "",
    @SerialName("HoursBack")
    val hoursBack: Int? = 12,
    @SerialName("NewName")
    val newName: String? = ""
)