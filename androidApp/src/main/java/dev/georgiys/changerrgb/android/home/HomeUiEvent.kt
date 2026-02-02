package dev.georgiys.changerrgb.android.home

sealed interface HomeUiEvent {
    data class OpenItemChip(val chipType: String, val chipId: Long, val chipName: String) : HomeUiEvent
    data class OpenItemAxis(val chipId: Long, val typeDevice: String): HomeUiEvent
}