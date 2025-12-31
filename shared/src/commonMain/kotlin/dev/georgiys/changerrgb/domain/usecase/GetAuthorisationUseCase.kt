package dev.georgiys.changerrgb.domain.usecase

import dev.georgiys.changerrgb.data.data.AuthRequest
import dev.georgiys.changerrgb.data.data.Authorisation
import dev.georgiys.changerrgb.domain.repository.ChangerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class GetAuthorisationUseCase: KoinComponent {
    private val repository: ChangerRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(authorisation: Authorisation): AuthRequest {
        return repository.getAuth(authorisation)
    }
}