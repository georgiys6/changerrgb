package dev.georgiys.changerrgb.android.itemchip

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.georgiys.changerrgb.android.home.HomeUiEvent
import dev.georgiys.changerrgb.android.itemchip.viewstate.ItemChipScreenState
import dev.georgiys.changerrgb.data.data.LedController
import dev.georgiys.changerrgb.domain.usecase.GetDataFromDBUseCase
import dev.georgiys.changerrgb.domain.usecase.GetDeviceStateUseCase
import dev.georgiys.changerrgb.domain.usecase.SetBrightnessToLedUseCase
import dev.georgiys.changerrgb.domain.usecase.SetSpeedToLedUseCase
import dev.georgiys.changerrgb.domain.usecase.SetStateToLedUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlin.collections.listOf

class ItemChipViewModel(
    private val getDeviceStateUseCase: GetDeviceStateUseCase,
    private val setStateToLedUseCase: SetStateToLedUseCase,
    private val setSpeedToLedUseCase: SetSpeedToLedUseCase,
    private val setBrightnessToLedUseCase: SetBrightnessToLedUseCase,
    private val getDataFromDBUseCase: GetDataFromDBUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val chipId: Long =
        savedStateHandle.get<Long>("chipId")
            ?: error("chipId is required")

    private val chipType: String =
        savedStateHandle.get<String>("chipType")
            ?: error("chipType is required")

    private val _uiState = MutableStateFlow(
        ItemChipScreenState(
            loading = true,
            chipId = chipId,
            type = chipType
        )
    )
    val uiState: StateFlow<ItemChipScreenState> = _uiState

    private val _event = MutableSharedFlow<HomeUiEvent>()
    val event = _event.asSharedFlow()

    init {
        load()
    }

    fun onAxisClick(chipId: Long, typeDevice: String) {
        viewModelScope.launch {
            _event.emit(HomeUiEvent.OpenItemAxis(chipId, typeDevice))
        }
    }

    fun initAxis(chipId: Long, typeDevice: String){
        viewModelScope.launch {
            try {
                val response = getDataFromDBUseCase(chipId, typeDevice)
                _uiState.update {
                    it.copy(
                        loading = false,
                        axis = response.response
                    )
                }
            } catch (error: Exception) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        errorMessage = "Could not connect: ${error.localizedMessage}"
                    )
                }
            }
        }
    }

    private fun load() {
        viewModelScope.launch {
            try {
                val response = getDeviceStateUseCase(chipId)
                _uiState.update {
                    it.copy(
                        loading = false,
                        item = response.response
                    )
                }
            } catch (error: Exception) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        errorMessage = "Could not connect: ${error.localizedMessage}"
                    )
                }
            }
        }
    }

    fun setStateToLed(ledController: LedController){
        viewModelScope.launch {
            try {
                val response = setStateToLedUseCase(ledController)
                if (response.status == "success") {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            item = Json.encodeToJsonElement(ledController)
                        )
                    }
                } else {
                    throw Exception()
                }
            } catch (error: Exception) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        errorMessage = "Could not connect: ${error.localizedMessage}"
                    )
                }
            }
        }
    }

    fun setSpeedToLed(ledController: LedController){
        viewModelScope.launch {
            try {
                val response = setSpeedToLedUseCase(ledController)
                if (response.status == "success") {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            item = Json.encodeToJsonElement(ledController)
                        )
                    }
                } else {
                    throw Exception()
                }
            } catch (error: Exception) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        errorMessage = "Could not connect: ${error.localizedMessage}"
                    )
                }
            }
        }
    }

    fun setBrightnessToLed(ledController: LedController){
        viewModelScope.launch {
            try {
                val response = setBrightnessToLedUseCase(ledController)
                if (response.status == "success") {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            item = Json.encodeToJsonElement(ledController)
                        )
                    }
                } else {
                    throw Exception()
                }
            } catch (error: Exception) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        errorMessage = "Could not connect: ${error.localizedMessage}"
                    )
                }
            }
        }
    }
}
