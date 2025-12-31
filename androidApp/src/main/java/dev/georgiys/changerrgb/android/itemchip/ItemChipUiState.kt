package dev.georgiys.changerrgb.android.itemchip

sealed interface ItemChipUiState {
    object Loading : ItemChipUiState
//    data class Content(val type: DeviceType, val item: DeviceState) : ItemChipUiState
    data class Error(val message: String) : ItemChipUiState
}
