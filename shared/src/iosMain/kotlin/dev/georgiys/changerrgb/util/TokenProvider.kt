package dev.georgiys.changerrgb.util

class IOSTokenProvider: TokenProvider {
    override val token: String
        get() = ""
}

actual fun provideToken(): TokenProvider = IOSTokenProvider()