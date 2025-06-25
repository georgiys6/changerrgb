package dev.georgiys.changerrgb

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform