package dev.georgiys.changerrgb.domain.usecase

import dev.georgiys.changerrgb.domain.repository.ChangerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class DownloadApkUseCase: KoinComponent {
    private val repository: ChangerRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(url: String) = repository.downloadApk(url)

}