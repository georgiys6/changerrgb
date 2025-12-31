package dev.georgiys.changerrgb.android

import android.app.Application
import org.koin.core.context.startKoin
import dev.georgiys.changerrgb.android.di.appModule
import dev.georgiys.changerrgb.di.getSharedModules
import dev.georgiys.changerrgb.util.ApplicationHolder
import org.koin.android.ext.koin.androidContext

class ChangerRGB : Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationHolder.appContext = this
        startKoin {
            androidContext(this@ChangerRGB)
            modules(appModule + getSharedModules())
        }
    }
}