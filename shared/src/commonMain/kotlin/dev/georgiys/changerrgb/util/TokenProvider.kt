package dev.georgiys.changerrgb.util

internal interface TokenProvider {
    val token: String
}

internal expect fun provideToken(): TokenProvider