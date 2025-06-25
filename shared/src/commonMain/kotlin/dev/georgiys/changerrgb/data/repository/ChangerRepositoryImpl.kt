package dev.georgiys.changerrgb.data.repository

import dev.georgiys.changerrgb.data.remote.Device
import dev.georgiys.changerrgb.data.remote.RemoteDataSource
import dev.georgiys.changerrgb.domain.repository.ChangerRepository

internal class ChangerRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : ChangerRepository {
    override suspend fun getConnect(): Boolean {
        return remoteDataSource.getConnect().isConnect
    }

    override suspend fun getDeviceList(): List<Device> {
        return remoteDataSource.getDeviceList()
    }

}