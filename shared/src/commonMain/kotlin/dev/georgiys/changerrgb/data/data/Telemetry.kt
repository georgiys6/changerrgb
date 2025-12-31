package dev.georgiys.changerrgb.data.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Telemetry (
    @SerialName("TypeMesseage")
    val typeMessage: String,
    @SerialName("Data")
    val data: List<DataTelemetry> = listOf(),
    @SerialName("ChipId")
    val chipId: Long
): TypeDevice()