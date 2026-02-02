package dev.georgiys.changerrgb.android.itemchip.viewstate

import kotlinx.serialization.json.JsonElement

data class ItemChipScreenState(
    val loading: Boolean = false,
    val chipId: Long = 0,
    val chipName: String = "",
    val type: String = "",
    val item: JsonElement? = null,
    val axis: List<JsonElement>? = null,
    val errorMessage: String? = null
)
