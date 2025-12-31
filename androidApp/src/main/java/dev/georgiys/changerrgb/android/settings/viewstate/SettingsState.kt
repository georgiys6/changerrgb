package dev.georgiys.changerrgb.android.settings.viewstate

data class SettingsState(
    val url: String = "",
    val login: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val error: String? = null
)
