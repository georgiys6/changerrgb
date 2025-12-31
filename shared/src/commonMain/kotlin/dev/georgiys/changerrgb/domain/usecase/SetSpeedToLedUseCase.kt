package dev.georgiys.changerrgb.domain.usecase

import dev.georgiys.changerrgb.data.data.LedController
import dev.georgiys.changerrgb.data.data.Response
import dev.georgiys.changerrgb.domain.repository.ChangerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class SetSpeedToLedUseCase: KoinComponent {
    private val repository: ChangerRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(ledController: LedController): Response{
        return repository.setSpeedToLed(ledController)
    }
}