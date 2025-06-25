package dev.georgiys.changerrgb.android

import android.app.Application
import org.koin.core.context.startKoin
import dev.georgiys.changerrgb.android.di.appModule
import dev.georgiys.changerrgb.di.getSharedModules

class ChangerRGB : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}