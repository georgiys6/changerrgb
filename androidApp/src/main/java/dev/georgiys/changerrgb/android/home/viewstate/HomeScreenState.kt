package dev.georgiys.changerrgb.android.home.viewstate

import dev.georgiys.changerrgb.data.data.Device
import dev.georgiys.changerrgb.data.data.TypeDevice
import kotlinx.serialization.json.JsonElement

data class HomeScreenState(
    var loading: Boolean = false,
    var refreshing: Boolean = false,
    var isConnect: Boolean = false,
    var errorMessage: String? = null,
    var loadFinished: Boolean = false,
    val deviceList: List<Device> = listOf(),
    val typeDevice: String = "",
    val selectedDeviceState: JsonElement? = null,
)