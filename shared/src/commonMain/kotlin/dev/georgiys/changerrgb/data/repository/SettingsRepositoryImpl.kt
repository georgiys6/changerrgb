package dev.georgiys.changerrgb.data.repository

import dev.georgiys.changerrgb.data.data.Authorisation
import dev.georgiys.changerrgb.domain.repository.SettingsRepository
import dev.georgiys.changerrgb.settings.KeyValueStorage

class SettingsRepositoryImpl(
    private val storage: KeyValueStorage
) : SettingsRepository {

    override fun getSettings(): Authorisation {
        return Authorisation(
            url = storage.getString("url"),
            login = storage.getString("login"),
            password = storage.getString("password")
        )
    }

    override fun saveSettings(url: String, login: String, password: String) {
        storage.putString("url", url)
        storage.putString("login", login)
        storage.putString("password", password)
    }

    override fun saveToken(token: String) {
        storage.putString("token", token)
    }

    override fun getToken(): String = storage.getString("token")
}