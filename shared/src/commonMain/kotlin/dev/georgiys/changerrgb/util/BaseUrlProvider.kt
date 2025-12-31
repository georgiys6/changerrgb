package dev.georgiys.changerrgb.util

interface BaseUrlProvider {
    val baseUrl: String
}

internal expect fun provideBaseUrl(): BaseUrlProvider