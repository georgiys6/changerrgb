package dev.georgiys.changerrgb.data.remote

import dev.georgiys.changerrgb.util.Dispatcher
import kotlinx.coroutines.withContext

internal class RemoteDataSource(
    private val apiService: ChangerService,
    private val dispatcher: Dispatcher,
) {
    suspend fun getConnect() = withContext(dispatcher.io){
        apiService.getConnect()
    }

    suspend fun getDeviceList() = withContext(dispatcher.io){
        apiService.getDeviceList()
    }
}