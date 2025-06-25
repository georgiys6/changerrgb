package dev.georgiys.changerrgb.domain.usecase

import dev.georgiys.changerrgb.domain.repository.ChangerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetConnectUseCase: KoinComponent {
    private val repository: ChangerRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(): Boolean{
        return repository.getConnect()
    }
}