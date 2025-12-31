package dev.georgiys.changerrgb.domain.usecase

import dev.georgiys.changerrgb.domain.repository.SettingsRepository

class SaveTokenUseCase(
    private val repository: SettingsRepository
){
    operator fun invoke(token: String) {
        repository.saveToken(token)
    }
}