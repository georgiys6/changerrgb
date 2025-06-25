package dev.georgiys.changerrgb.android.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.georgiys.changerrgb.data.remote.Device
import dev.georgiys.changerrgb.domain.usecase.GetConnectUseCase
import dev.georgiys.changerrgb.domain.usecase.GetDeviceListUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    val getConnectUseCase: GetConnectUseCase,
    val getDeviceListUseCase: GetDeviceListUseCase
): ViewModel() {
    var uiState by mutableStateOf(HomeScreenState())
    private val _isSuccess = MutableStateFlow(uiState.isConnect)
    val isSuccess: StateFlow<Boolean> = _isSuccess.asStateFlow()

    fun resetSuccess() {
        _isSuccess.value = false
    }

    fun fakeGetConnect() {
        if (uiState.loading) return

        viewModelScope.launch {
            uiState = uiState.copy(
                loading = true,
                refreshing = true
            )

            try {
                val resultGetConnect = true

                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    isConnect = true,
                    loadFinished = resultGetConnect,
                )

            } catch (error: Throwable) {
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    isConnect = false,
                    loadFinished = true,
                    errorMessage = "Could not connect: ${error.localizedMessage}"
                )
            }
            finally {
                _isSuccess.value = true
            }
        }
    }
    fun getConnect(){
        if (uiState.loading) return

        viewModelScope.launch {
            uiState = uiState.copy(
                loading = true
            )

            try {
                val resultGetConnect = getConnectUseCase()
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = resultGetConnect,
                )

            } catch (error: Throwable){
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = true,
                    errorMessage = "Could not connect: ${error.localizedMessage}"
                )
            }
        }
    }

    fun getDeviceList(){
        if (uiState.loading) return

        viewModelScope.async<List<Device>> {
            awaitGetDeviceList()
        }
    }

    fun getFakeDeviceList(){
        if (uiState.loading) return

        viewModelScope.async<List<Device>> {
            awaitFakeGetDeviceList()
        }
    }

    private suspend fun awaitFakeGetDeviceList(): List<Device>{
        var deviceList: List<Device>
        uiState = uiState.copy(
            loading = true
        )
        try {
//            deviceList = getDeviceListUseCase()
            deviceList = listOf(
                Device(
                    chipId = 1,
                    typeDevice = "Telemetry",
                    deviceName = "Тестовый"
//                    uuid = 1,
//                    type = "Shit",
//                    temperature = 37.7,
//                    humidity = 451.1,
//                    isSensor = false,
//                    co2ppm = 696
                )
            )
            uiState = uiState.copy(
                loading = false,
                refreshing = false,
                deviceList = deviceList
            )
            deviceList
        } catch (error: Throwable){
            uiState = uiState.copy(
                loading = false,
                refreshing = false,
                loadFinished = true,
                deviceList = listOf(),
                errorMessage = "Could not connect: ${error.localizedMessage}"
            )
            deviceList = listOf<Device>()
        }
        return deviceList
    }

    private suspend fun awaitGetDeviceList(): List<Device>{
        var deviceList: List<Device>
        uiState = uiState.copy(
            loading = true
        )
        try {
            deviceList = getDeviceListUseCase()
            uiState = uiState.copy(
                loading = false,
                refreshing = false,
                deviceList = deviceList,
            )
        } catch (error: Throwable){
            uiState = uiState.copy(
                loading = false,
                refreshing = false,
                loadFinished = true,
                errorMessage = "Could not connect: ${error.localizedMessage}"
            )
            deviceList = listOf<Device>()
        }
        return deviceList
    }
}

data class HomeScreenState(
    var loading: Boolean = false,
    var refreshing: Boolean = false,
    var isConnect: Boolean = false,
    var errorMessage: String? = null,
    var loadFinished: Boolean = false,
    val deviceList: List<Device> = listOf<Device>()
)