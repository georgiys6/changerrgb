package dev.georgiys.changerrgb.util

interface TokenProvider {
    val token: String
}

expect fun provideToken(): TokenProvider