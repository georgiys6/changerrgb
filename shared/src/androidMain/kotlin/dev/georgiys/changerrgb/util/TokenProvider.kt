package dev.georgiys.changerrgb.util

internal class AndroidTokenProvider: TokenProvider {
    override val token: String
        get() = ""
}

internal actual fun provideToken(): TokenProvider = AndroidTokenProvider()