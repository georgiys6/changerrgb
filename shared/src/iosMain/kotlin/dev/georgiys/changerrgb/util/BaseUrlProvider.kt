package dev.georgiys.changerrgb.util



internal class IOSBaseUrlProvider: BaseUrlProvider {
    override val baseUrl: String
        get() = "localhost"
}

internal actual fun provideBaseUrl(): BaseUrlProvider = IOSBaseUrlProvider()