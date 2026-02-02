package dev.georgiys.changerrgb.android.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.georgiys.changerrgb.android.BuildConfig
import dev.georgiys.changerrgb.android.home.viewstate.HomeScreenState
import dev.georgiys.changerrgb.android.util.ApkInstaller
import dev.georgiys.changerrgb.data.data.Device
import dev.georgiys.changerrgb.domain.usecase.DownloadApkUseCase
import dev.georgiys.changerrgb.domain.usecase.GetDeviceListUseCase
import dev.georgiys.changerrgb.domain.usecase.GetReleasesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class HomeViewModel(
    val getDeviceListUseCase: GetDeviceListUseCase,
    val getReleasesUseCase: GetReleasesUseCase,
    val downloadApkUseCase: DownloadApkUseCase,
    private val apkInstaller: ApkInstaller,
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<HomeUiEvent>()
    val event = _event.asSharedFlow()

    fun onDeviceClicked(device: Device) {
        viewModelScope.launch {
            _event.emit(HomeUiEvent.OpenItemChip(
                device.typeDevice,
                device.chipId,
                device.deviceName
            ))
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

    fun getReleases(){
        viewModelScope.launch {
            try {
                val infoReleases = getReleasesUseCase()
                val verRepGithub = infoReleases.first().tagName.toDouble()
                _uiState.update {
                    it.copy(
                        urlUpdateApp = infoReleases.first().assets[0].browserDownloadUrl,
                        isUpdate = verRepGithub > BuildConfig.VERSION_NAME.toDouble()
                    )
                }
            } catch (_: Throwable) {
            }
        }
    }

    fun updateApp() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            try {
                val apkBytes = downloadApkUseCase(_uiState.value.urlUpdateApp)
                apkInstaller.install(apkBytes)

                _uiState.update { it.copy(loading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(loading = false, errorMessage = "Update failed: ${e.message}")
                }
            }
        }
    }

}