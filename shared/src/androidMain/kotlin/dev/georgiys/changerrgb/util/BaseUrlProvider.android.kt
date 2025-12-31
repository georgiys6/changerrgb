package dev.georgiys.changerrgb.util

import android.content.Context
import android.content.SharedPreferences

object ApplicationHolder {
    lateinit var appContext: Context
}

class AndroidBaseUrlProvider(
    private val prefs: SharedPreferences
): BaseUrlProvider {
    override val baseUrl: String
        get() = prefs.getString("url", "localhost") ?: "localhost"
}


actual fun provideBaseUrl(): BaseUrlProvider {
    val prefs =
        ApplicationHolder.appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)

    return AndroidBaseUrlProvider(prefs)
}