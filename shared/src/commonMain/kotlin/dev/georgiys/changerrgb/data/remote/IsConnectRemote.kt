package dev.georgiys.changerrgb.data.remote

import kotlinx.serialization.Serializable

@Serializable
internal data class IsConnectRemote (
    val isConnect: Boolean = false
)