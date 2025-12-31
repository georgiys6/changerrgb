package dev.georgiys.changerrgb.android.settings.viewstate

sealed interface SettingsEvent {
    data class UrlChanged(val value: String) : SettingsEvent
    data class LoginChanged(val value: String) : SettingsEvent
    data class PasswordChanged(val value: String) : SettingsEvent

    object SaveClicked : SettingsEvent
    object Load : SettingsEvent
}
