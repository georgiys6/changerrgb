package dev.georgiys.changerrgb.domain.repository

import dev.georgiys.changerrgb.data.data.AuthRequest
import dev.georgiys.changerrgb.data.data.Authorisation
import dev.georgiys.changerrgb.data.data.Device
import dev.georgiys.changerrgb.data.data.LedController
import dev.georgiys.changerrgb.data.data.Response
import dev.georgiys.changerrgb.data.data.StateListResponse
import dev.georgiys.changerrgb.data.data.StateResponse
import dev.georgiys.changerrgb.data.data.Telemetry

internal interface ChangerRepository {
    suspend fun getConnect(): Boolean
    suspend fun getDeviceList(): List<Device>
    suspend fun getDeviceState(chipId: Long): StateResponse
    suspend fun getAuth(authorisation: Authorisation): AuthRequest
    suspend fun setStateToLed(ledController: LedController): Response
    suspend fun setSpeedToLed(ledController: LedController): Response
    suspend fun setBrightnessToLed(ledController: LedController): Response
    suspend fun getDataFromDB(chipId: Long, typeDevice: String): StateListResponse
}