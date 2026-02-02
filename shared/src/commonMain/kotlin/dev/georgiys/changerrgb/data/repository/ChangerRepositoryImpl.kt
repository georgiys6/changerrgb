package dev.georgiys.changerrgb.data.repository

import dev.georgiys.changerrgb.data.data.Authorisation
import dev.georgiys.changerrgb.data.data.LedController
import dev.georgiys.changerrgb.data.data.Response
import dev.georgiys.changerrgb.data.remote.RemoteDataSource
import dev.georgiys.changerrgb.domain.repository.ChangerRepository

internal class ChangerRepositoryImpl(
    val remoteDataSource: RemoteDataSource
) : ChangerRepository {
    override suspend fun getConnect() = remoteDataSource.getConnect().isConnect

    override suspend fun getDeviceList() = remoteDataSource.getDeviceList()

    override suspend fun getDeviceState(chipId: Long) = remoteDataSource.getDeviceState(chipId)

    override suspend fun getAuth(authorisation: Authorisation) =
        remoteDataSource.getAuth(authorisation)

    override suspend fun setStateToLed(ledController: LedController) =
        remoteDataSource.setStateToLed(ledController)

    override suspend fun setSpeedToLed(ledController: LedController) =
        remoteDataSource.setSpeedToLed(ledController)

    override suspend fun setBrightnessToLed(ledController: LedController) =
        remoteDataSource.setBrightnessToLed(ledController)

    override suspend fun getDataFromDB(
        chipId: Long,
        typeDevice: String
    ) = remoteDataSource.getDataFromDB(chipId,typeDevice)

    override suspend fun setNewChipName(
        chipId: Long,
        chipName: String
    ): Response = remoteDataSource.setNewChipName(chipId, chipName)

    override suspend fun getReleases() = remoteDataSource.getReleases()

    override suspend fun downloadApk(url: String) = remoteDataSource.downloadApk(url)
}