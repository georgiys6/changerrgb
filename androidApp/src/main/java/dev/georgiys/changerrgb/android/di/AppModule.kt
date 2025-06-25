package dev.georgiys.changerrgb.android.di

import dev.georgiys.changerrgb.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel{ HomeViewModel(get(), get()) }
}