package dev.georgiys.changerrgb.di

import dev.georgiys.changerrgb.data.remote.ChangerService
import dev.georgiys.changerrgb.data.remote.RemoteDataSource
import dev.georgiys.changerrgb.data.repository.ChangerRepositoryImpl
import dev.georgiys.changerrgb.domain.repository.ChangerRepository
import dev.georgiys.changerrgb.domain.usecase.GetConnectUseCase
import dev.georgiys.changerrgb.domain.usecase.GetDeviceListUseCase
import dev.georgiys.changerrgb.util.provideDispatcher
import dev.georgiys.changerrgb.util.provideToken
import org.koin.dsl.module

private val dataModule = module {
    factory { RemoteDataSource(get(), get()) }
    factory { ChangerService(get()) }
}

private val utilityModule = module {
    factory { provideDispatcher() }
    factory { provideToken() }
}

private val domainModule = module {
    single<ChangerRepository> { ChangerRepositoryImpl(get()) }
    factory { GetConnectUseCase() }
    factory { GetDeviceListUseCase() }
}

private val sharedModules = listOf(dataModule, utilityModule, domainModule)

fun getSharedModules() = sharedModules