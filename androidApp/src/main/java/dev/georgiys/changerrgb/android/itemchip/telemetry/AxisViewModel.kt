package dev.georgiys.changerrgb.android.itemchip.telemetry

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.georgiys.changerrgb.domain.usecase.GetDataFromDBUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AxisViewModel(
    private val getDataFromDBUseCase: GetDataFromDBUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val chipId: Long =
        savedStateHandle["chipId"] ?: error("chipId required")

    private val typeDevice: String =
        savedStateHandle["typeDevice"] ?: error("typeDevice required")

    private val _uiState = MutableStateFlow(AxisScreenState(
        loading = true,
        typeDevice = typeDevice
    ))
    val uiState: StateFlow<AxisScreenState> = _uiState

    init {
        loadAxis()
    }

    private fun loadAxis() {
        viewModelScope.launch {
            try {
                val response = getDataFromDBUseCase(chipId, typeDevice)
                _uiState.update {
                    it.copy(
                        loading = false,
                        points = response.response
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        loading = false,
                        errorMessage = e.localizedMessage
                    )
                }
            }
        }
    }
}
