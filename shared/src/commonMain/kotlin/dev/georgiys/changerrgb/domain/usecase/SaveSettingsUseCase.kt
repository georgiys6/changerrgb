package dev.georgiys.changerrgb.domain.usecase

import dev.georgiys.changerrgb.domain.repository.SettingsRepository

class SaveSettingsUseCase(
    private val repository: SettingsRepository
) {
    operator fun invoke(url: String, login: String, password: String) {
        repository.saveSettings(url, login, password)
    }
}