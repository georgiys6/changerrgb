package dev.georgiys.changerrgb.data.remote

import dev.georgiys.changerrgb.util.BaseUrlProvider
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal abstract class KtorApi(
    baseUrlProvider: BaseUrlProvider
) {
    private val baseUrl = baseUrlProvider.baseUrl

    val client = HttpClient{
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
                classDiscriminator = "TypeMesseage"
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15_000
            connectTimeoutMillis = 10_000
            socketTimeoutMillis = 15_000
        }
    }

    fun HttpRequestBuilder.pathUrl(path: String){
        url {
            protocol = URLProtocol.HTTPS
            host = baseUrl
            path(path)
        }
    }
}