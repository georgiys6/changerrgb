package dev.georgiys.changerrgb.data.remote

import dev.georgiys.changerrgb.util.TokenProvider
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post

internal class ChangerService(tokenProvider: TokenProvider) : KtorApi(tokenProvider) {

    suspend fun getConnect(): IsConnectRemote = client.get {
//        pathUrl("movie/popular")
//        parameter("page", page)
    }.body()

    suspend fun getDeviceList(): List<Device> = client.post {
        pathUrl("api/devices")

    }.body()

//    suspend fun getMovie(movieId: Int): MovieRemote = client.get {
//        pathUrl("movie/$movieId")
//    }.body()
}