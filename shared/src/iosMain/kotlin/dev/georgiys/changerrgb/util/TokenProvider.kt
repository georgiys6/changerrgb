package dev.georgiys.changerrgb.util

internal class IOSTokenProvider: TokenProvider {
    override val token: String
        get() = ""
}

internal actual fun provideToken(): TokenProvider = IOSTokenProvider()