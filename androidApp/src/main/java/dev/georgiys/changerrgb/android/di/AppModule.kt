package dev.georgiys.changerrgb.android.di

import android.content.Context
import android.content.SharedPreferences
import dev.georgiys.changerrgb.android.home.HomeViewModel
import dev.georgiys.changerrgb.android.itemchip.ItemChipViewModel
import dev.georgiys.changerrgb.android.itemchip.telemetry.AxisViewModel
import dev.georgiys.changerrgb.android.settings.KeyValueStorageAndroid
import dev.georgiys.changerrgb.android.settings.SettingsViewModel
import dev.georgiys.changerrgb.settings.KeyValueStorage
import dev.georgiys.changerrgb.util.AndroidBaseUrlProvider
import dev.georgiys.changerrgb.util.BaseUrlProvider
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel{
        HomeViewModel(get(),get(), get(), get())
    }

    viewModel{
        SettingsViewModel(get(),get(),get(), get())
    }

    viewModel {
        ItemChipViewModel(
            getDeviceStateUseCase = get(),
            setStateToLedUseCase = get(),
            setSpeedToLedUseCase = get(),
            setBrightnessToLedUseCase = get(),
            getDataFromDBUseCase = get(),
            savedStateHandle = get()
        )
    }

    viewModel{
        AxisViewModel(
            getDataFromDBUseCase = get(),
            savedStateHandle = get()
        )
    }

    single<KeyValueStorage> {
        KeyValueStorageAndroid(androidContext())
    }
    single<SharedPreferences> {
        androidContext().getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    single<BaseUrlProvider> {
        AndroidBaseUrlProvider(get())
    }
}