package dev.georgiys.changerrgb.domain.usecase

import dev.georgiys.changerrgb.data.data.Authorisation
import dev.georgiys.changerrgb.domain.repository.SettingsRepository

class GetSettingsUseCase(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Authorisation {
        return repository.getSettings()
    }
}