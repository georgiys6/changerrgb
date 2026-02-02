package dev.georgiys.changerrgb.domain.usecase

import dev.georgiys.changerrgb.domain.repository.ChangerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class GetReleasesUseCase: KoinComponent {
    private val repository: ChangerRepository by inject()
    suspend operator fun invoke() = repository.getReleases()
}