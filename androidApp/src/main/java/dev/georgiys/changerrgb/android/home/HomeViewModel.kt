package dev.georgiys.changerrgb.android.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.georgiys.changerrgb.android.home.viewstate.HomeScreenState
import dev.georgiys.changerrgb.data.data.Device
import dev.georgiys.changerrgb.domain.usecase.GetConnectUseCase
import dev.georgiys.changerrgb.domain.usecase.GetDeviceListUseCase
import dev.georgiys.changerrgb.domain.usecase.GetDeviceStateUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

internal class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
    val getConnectUseCase: GetConnectUseCase,
    val getDeviceListUseCase: GetDeviceListUseCase,
    val getDeviceStateUseCase: GetDeviceStateUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<HomeUiEvent>()
    val event = _event.asSharedFlow()

    fun onDeviceClicked(device: Device) {
        viewModelScope.launch {
            _event.emit(HomeUiEvent.OpenItemChip(device.typeDevice, device.chipId))
        }
    }

    fun getConnect(){
        if (_uiState.value.loading) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                loading = true
            )

            try {
                val resultGetConnect = getConnectUseCase()
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = resultGetConnect,
                )

            } catch (error: Throwable){
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = true,
                    errorMessage = "Could not connect: ${error.localizedMessage}"
                )
            }
        }
    }

    fun getDeviceList() {
        if (_uiState.value.loading) return

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    loading = true,
                    errorMessage = null
                )
            }

            try {
                val deviceList = getDeviceListUseCase()
                _uiState.update {
                    it.copy(
                        loading = false,
                        refreshing = false,
                        deviceList = deviceList
                    )
                }
            } catch (error: Throwable) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        refreshing = false,
                        errorMessage = "Could not connect: ${error.localizedMessage}"
                    )
                }
            }
        }
    }

    private suspend fun awaitGetDeviceList(): List<Device>{
        var deviceList: List<Device>
        _uiState.value = _uiState.value.copy(
            loading = true
        )
        try {
            deviceList = getDeviceListUseCase()
            _uiState.value = _uiState.value.copy(
                loading = false,
                refreshing = false,
                deviceList = deviceList,
            )
        } catch (error: Throwable){
            _uiState.value = _uiState.value.copy(
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