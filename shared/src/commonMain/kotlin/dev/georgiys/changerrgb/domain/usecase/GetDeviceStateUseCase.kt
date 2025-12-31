package dev.georgiys.changerrgb.domain.usecase

import dev.georgiys.changerrgb.data.data.StateResponse
import dev.georgiys.changerrgb.domain.repository.ChangerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetDeviceStateUseCase: KoinComponent {
    private val repository: ChangerRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(chipId: Long): StateResponse{
        return repository.getDeviceState(chipId)
    }
}