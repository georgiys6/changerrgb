package dev.georgiys.changerrgb.data.remote

import dev.georgiys.changerrgb.data.data.AuthRequest
import dev.georgiys.changerrgb.data.data.Authorisation
import dev.georgiys.changerrgb.data.data.Command
import dev.georgiys.changerrgb.data.data.Device
import dev.georgiys.changerrgb.data.data.LedController
import dev.georgiys.changerrgb.data.data.Response
import dev.georgiys.changerrgb.data.data.StateListResponse
import dev.georgiys.changerrgb.data.data.StateResponse
import dev.georgiys.changerrgb.data.data.Telemetry
import dev.georgiys.changerrgb.util.BaseUrlProvider
import dev.georgiys.changerrgb.util.TokenProvider
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

internal class ChangerService(
    val tokenProvider: TokenProvider,
    baseUrlProvider: BaseUrlProvider
) : KtorApi(baseUrlProvider) {

    suspend fun getConnect(): IsConnectRemote = client.get {
//        pathUrl("movie/popular")
//        parameter("page", page)
    }.body()
    suspend fun getDeviceList(): List<Device> {
        val response: HttpResponse = client.post {
            val body = Command(
                typeMesseage = "GetListDevice",
                token = tokenProvider.token
            )
            pathUrl("smarthome/Admin")
            contentType(ContentType.Application.Json)
            setBody(body)
            header("Accept", "application/json")
        }
        val responseText = response.bodyAsText()
        println("Raw response: $responseText")

        return if (responseText == "[]") {
            emptyList()
        } else {
            Json.decodeFromString(responseText)
        }
    }

    suspend fun getDeviceState(chipId: Long): StateResponse {
        return client.post {
            pathUrl("smarthome/Device/SendMesseage")
            contentType(ContentType.Application.Json)
            setBody(
                Command(
                    chipId = chipId,
                    typeMesseage = "GetState",
                    token = tokenProvider.token
                )
            )
        }.body()
    }

    suspend fun getAuth(auth: Authorisation): AuthRequest {

        val response: HttpResponse = client.post {
            val body = Command(
                login = auth.login,
                password = auth.password,
                typeMesseage = "Auth"
            )
            pathUrl("smarthome/Auth")
            contentType(ContentType.Application.Json)
            setBody(body)
            header("Accept", "application/json")
        }

        val responseText = response.bodyAsText()

        return Json.decodeFromString(responseText)
    }

    suspend fun setStateToLed(ledController: LedController): Response{
        return client.post {
            pathUrl("smarthome/Device/SendMesseage")
            contentType(ContentType.Application.Json)
            setBody(
                Command(
                    mode = ledController.mode,
                    colorR = ledController.colorR,
                    colorG = ledController.colorG,
                    colorB = ledController.colorB,
                    chipId = ledController.chipId,
                    typeMesseage = "SetStateToLed",
                    token = tokenProvider.token
                )
            )
        }.body()
    }

    suspend fun setSpeedToLed(ledController: LedController): Response {
        return client.post {
            pathUrl("smarthome/Device/SendMesseage")
            contentType(ContentType.Application.Json)
            setBody(
                Command(
                    chipId = ledController.chipId,
                    speed = ledController.speed.toInt(),
                    typeMesseage = "SetSpeedToLed",
                    token = tokenProvider.token
                )
            )
        }.body()
    }

    suspend fun setBrightnessToLed(ledController: LedController): Response {
        return client.post {
            pathUrl("smarthome/Device/SendMesseage")
            contentType(ContentType.Application.Json)
            setBody(
                Command(
                    chipId = ledController.chipId,
                    brightness = ledController.brightness.toInt(),
                    typeMesseage = "SetBrightnessToLed",
                    token = tokenProvider.token
                )
            )
        }.body()
    }

    suspend fun getDataFromDB(chipId: Long, typeDevice: String): StateListResponse {
        return client.post {
            pathUrl("smarthome/Device/SendMesseage")
            contentType(ContentType.Application.Json)
            setBody(
                Command(
                    chipId = chipId,
                    sensorType = typeDevice,
                    typeMesseage = "GetDataFromDB",
                    token = tokenProvider.token
                )
            )
        }.body()
    }
}