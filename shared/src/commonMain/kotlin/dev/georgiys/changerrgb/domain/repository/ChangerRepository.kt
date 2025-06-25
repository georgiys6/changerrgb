package dev.georgiys.changerrgb.domain.repository

import dev.georgiys.changerrgb.data.remote.Device

internal interface ChangerRepository {
    suspend fun getConnect(): Boolean
    suspend fun getDeviceList(): List<Device>
}