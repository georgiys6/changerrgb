package dev.georgiys.changerrgb.domain.usecase

import dev.georgiys.changerrgb.data.remote.Device
import dev.georgiys.changerrgb.domain.repository.ChangerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class GetDeviceListUseCase: KoinComponent {
    private val repository: ChangerRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(): List<Device>{
        return repository.getDeviceList()
    }
}