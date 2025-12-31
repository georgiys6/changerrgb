package dev.georgiys.changerrgb.android.settings.viewstate

sealed interface SettingsAction {
    data class Loaded(val url: String, val login: String, val password: String) : SettingsAction
    object Saved : SettingsAction
    data class Error(val message: String) : SettingsAction
}