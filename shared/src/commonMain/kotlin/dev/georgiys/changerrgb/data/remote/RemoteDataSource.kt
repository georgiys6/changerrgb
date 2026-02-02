package dev.georgiys.changerrgb.data.remote

import dev.georgiys.changerrgb.data.data.Authorisation
import dev.georgiys.changerrgb.data.data.LedController
import dev.georgiys.changerrgb.util.Dispatcher
import kotlinx.coroutines.withContext

internal class RemoteDataSource(
    val apiService: ChangerService,
    val dispatcher: Dispatcher,
) {
    suspend fun getConnect() = withContext(dispatcher.io) {
        apiService.getConnect()
    }

    suspend fun getDeviceList() = withContext(dispatcher.io) {
        apiService.getDeviceList()
    }

    suspend fun getDeviceState(chipId: Long) = withContext(dispatcher.io) {
        apiService.getDeviceState(chipId)
    }

    suspend fun getAuth(auth: Authorisation) = withContext(dispatcher.io) {
        apiService.getAuth(auth)
    }

    suspend fun setStateToLed(ledController: LedController) = withContext(dispatcher.io) {
        apiService.setStateToLed(ledController)
    }

    suspend fun setSpeedToLed(ledController: LedController) = withContext(dispatcher.io) {
        apiService.setSpeedToLed(ledController)
    }

    suspend fun setBrightnessToLed(ledController: LedController) = withContext(dispatcher.io) {
        apiService.setBrightnessToLed(ledController)
    }

    suspend fun getDataFromDB(chipId: Long, typeDevice: String) = withContext(dispatcher.io) {
        apiService.getDataFromDB(chipId, typeDevice)
    }

    suspend fun setNewChipName(chipId: Long, chipName: String) = withContext(dispatcher.io){
        apiService.setNewChipName(chipId, chipName)
    }

    suspend fun getReleases() = withContext(dispatcher.io) {
        apiService.getReleases()
    }

    suspend fun downloadApk(url: String) = withContext(dispatcher.io) {
        apiService.downloadApk(url)
    }
}