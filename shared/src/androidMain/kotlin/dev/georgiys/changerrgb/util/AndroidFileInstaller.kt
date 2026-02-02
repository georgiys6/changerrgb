package dev.georgiys.changerrgb.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

class AndroidFileInstaller(private val context: Context) : FileInstaller {
    override suspend fun installApk(bytes: ByteArray, fileName: String) {
        val file = File(context.cacheDir, fileName)
        file.writeBytes(bytes)

        val uri: Uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/vnd.android.package-archive")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}