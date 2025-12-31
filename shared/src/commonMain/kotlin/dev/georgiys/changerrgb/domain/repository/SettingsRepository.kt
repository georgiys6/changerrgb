package dev.georgiys.changerrgb.domain.repository

import dev.georgiys.changerrgb.data.data.Authorisation

interface SettingsRepository {
    fun getSettings(): Authorisation
    fun saveSettings(url: String, login: String, password: String)
    fun saveToken(token: String)
    fun getToken(): String
}