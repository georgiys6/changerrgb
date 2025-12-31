package dev.georgiys.changerrgb.settings

interface KeyValueStorage {
    fun getString(key: String, default: String = ""): String
    fun putString(key: String, value: String)
}