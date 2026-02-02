package dev.georgiys.changerrgb.util

interface FileInstaller {
    suspend fun installApk(bytes: ByteArray, fileName: String)
}