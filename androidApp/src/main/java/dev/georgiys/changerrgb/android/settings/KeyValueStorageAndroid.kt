package dev.georgiys.changerrgb.android.settings

import android.content.Context
import dev.georgiys.changerrgb.settings.KeyValueStorage

class KeyValueStorageAndroid(
    context: Context
) : KeyValueStorage {

    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun getString(key: String, default: String): String {
        return prefs.getString(key, default) ?: default
    }

    override fun putString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }
}