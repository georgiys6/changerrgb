package dev.georgiys.changerrgb.di

import dev.georgiys.changerrgb.data.remote.ChangerService
import dev.georgiys.changerrgb.data.remote.RemoteDataSource
import dev.georgiys.changerrgb.data.repository.ChangerRepositoryImpl
import dev.georgiys.changerrgb.data.repository.SettingsRepositoryImpl
import dev.georgiys.changerrgb.domain.repository.ChangerRepository
import dev.georgiys.changerrgb.domain.repository.SettingsRepository
import dev.georgiys.changerrgb.domain.usecase.GetAuthorisationUseCase
import dev.georgiys.changerrgb.domain.usecase.GetConnectUseCase
import dev.georgiys.changerrgb.domain.usecase.GetDataFromDBUseCase
import dev.georgiys.changerrgb.domain.usecase.GetDeviceListUseCase
import dev.georgiys.changerrgb.domain.usecase.GetDeviceStateUseCase
import dev.georgiys.changerrgb.domain.usecase.GetSettingsUseCase
import dev.georgiys.changerrgb.domain.usecase.SaveSettingsUseCase
import dev.georgiys.changerrgb.domain.usecase.SaveTokenUseCase
import dev.georgiys.changerrgb.domain.usecase.SetBrightnessToLedUseCase
import dev.georgiys.changerrgb.domain.usecase.SetSpeedToLedUseCase
import dev.georgiys.changerrgb.domain.usecase.SetStateToLedUseCase
import dev.georgiys.changerrgb.util.provideBaseUrl
import dev.georgiys.changerrgb.util.provideDispatcher
import dev.georgiys.changerrgb.util.provideToken
import org.koin.dsl.module

private val dataModule = module {
    factory { RemoteDataSource(get(), get()) }
    factory { ChangerService(get(), get()) }
}

private val utilityModule = module {
    factory { provideDispatcher() }
    factory { provideToken() }
    factory { provideBaseUrl() }
}

private val domainModule = module {
    single<ChangerRepository> { ChangerRepositoryImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    factory { GetConnectUseCase() }
    factory { GetDeviceListUseCase() }
    factory { GetDeviceStateUseCase() }
    factory { SetStateToLedUseCase() }
    factory { GetDataFromDBUseCase() }
    factory { SetSpeedToLedUseCase() }
    factory { SetBrightnessToLedUseCase() }
    factory { GetSettingsUseCase(get()) }
    factory { SaveSettingsUseCase(get()) }
    factory { SaveTokenUseCase(get()) }
    factory { GetAuthorisationUseCase() }
}

private val sharedModules = listOf(dataModule, utilityModule, domainModule)

fun getSharedModules() = sharedModules