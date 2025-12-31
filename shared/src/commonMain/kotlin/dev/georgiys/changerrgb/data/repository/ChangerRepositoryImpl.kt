package dev.georgiys.changerrgb.data.repository

import dev.georgiys.changerrgb.data.data.AuthRequest
import dev.georgiys.changerrgb.data.data.Authorisation
import dev.georgiys.changerrgb.data.data.Device
import dev.georgiys.changerrgb.data.data.LedController
import dev.georgiys.changerrgb.data.data.Response
import dev.georgiys.changerrgb.data.data.StateListResponse
import dev.georgiys.changerrgb.data.data.StateResponse
import dev.georgiys.changerrgb.data.data.Telemetry
import dev.georgiys.changerrgb.data.remote.RemoteDataSource
import dev.georgiys.changerrgb.domain.repository.ChangerRepository

internal class ChangerRepositoryImpl(
    val remoteDataSource: RemoteDataSource
) : ChangerRepository {
    override suspend fun getConnect(): Boolean {
        return remoteDataSource.getConnect().isConnect
    }
    override suspend fun getDeviceList(): List<Device> {
        return remoteDataSource.getDeviceList()
    }

    override suspend fun getDeviceState(chipId: Long): StateResponse {
        return remoteDataSource.getDeviceState(chipId)
    }

    override suspend fun getAuth(authorisation: Authorisation): AuthRequest {
        return remoteDataSource.getAuth(authorisation)
    }

    override suspend fun setStateToLed(ledController: LedController): Response {
        return remoteDataSource.setStateToLed(ledController)
    }

    override suspend fun setSpeedToLed(ledController: LedController): Response {
        return remoteDataSource.setSpeedToLed(ledController)
    }

    override suspend fun setBrightnessToLed(ledController: LedController): Response {
        return remoteDataSource.setBrightnessToLed(ledController)
    }

    override suspend fun getDataFromDB(
        chipId: Long,
        typeDevice: String
    ): StateListResponse {
        return remoteDataSource.getDataFromDB(chipId,typeDevice)
    }

}