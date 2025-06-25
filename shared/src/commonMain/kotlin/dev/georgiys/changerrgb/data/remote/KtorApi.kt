package dev.georgiys.changerrgb.data.remote

import dev.georgiys.changerrgb.util.TokenProvider
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL = "localhost"

internal abstract class KtorApi(
    private val tokenProvider: TokenProvider
) {

    val client = HttpClient{
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    fun HttpRequestBuilder.pathUrl(path: String){
        url {
            protocol = URLProtocol.HTTP
            host = BASE_URL
            path("docs/welcome.html")
            path("3", path)
            header(HttpHeaders.Authorization, "Bearer ${tokenProvider.token}")
        }
    }
}