package dev.georgiys.changerrgb.util

import android.content.Context
import android.content.SharedPreferences

class AndroidTokenProvider(
    private val prefs: SharedPreferences
): TokenProvider {
    override val token: String
        get() = prefs.getString("token", "1230") ?: "1254"
}

actual fun provideToken(): TokenProvider {
    val prefs =
        ApplicationHolder.appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)

    return AndroidTokenProvider(prefs)
}