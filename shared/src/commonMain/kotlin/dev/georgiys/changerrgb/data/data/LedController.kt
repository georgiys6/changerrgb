package dev.georgiys.changerrgb.data.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LedController(
    @SerialName("TypeMesseage")
    val typeMessage: String,
    @SerialName("Mode")
    var mode: Int,
    @SerialName("ColorR")
    var colorR: Int,
    @SerialName("ColorG")
    var colorG: Int,
    @SerialName("ColorB")
    var colorB: Int,
    @SerialName("Speed")
    var speed: Float,
    @SerialName("Brightness")
    var brightness: Float,
    @SerialName("ChipId")
    val chipId: Long
) : TypeDevice()