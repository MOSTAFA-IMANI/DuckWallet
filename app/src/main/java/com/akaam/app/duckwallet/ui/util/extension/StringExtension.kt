package com.akaam.app.duckwallet.ui.util.extension

import com.akaam.app.duckwallet.R

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun getDialIntent(string: String): Intent {
    val dialIntent = Intent(Intent.ACTION_DIAL)
    dialIntent.data = Uri.parse("tel:$string")
    return dialIntent

}

fun String.callNumber(activity: Activity) {
    val dialIntent = getDialIntent(this)
    activity.startActivity(dialIntent)
}

fun String.openBrowser(context: Context) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(this)
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context,
            context.getString(R.string.error_open_browser) + this,
            Toast.LENGTH_SHORT).show()
    }
}



private fun parseStringToData(input: String, format: SimpleDateFormat): Date? {
    try {
        input.let {
            return format.parse(it)
        }
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return null
}

fun String?.parseToDate(): Date? {
    if (this.isNullOrEmpty()) {
        return null
    }
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    val formatWithoutExactTime = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    return parseStringToData(this, format) ?: parseStringToData(this, formatWithoutExactTime)
}

fun String?.replaceIfEmpty(replacement: String?): String {
    return if (this.isNullOrEmpty())
        replacement ?: ""
    else
        this
}

fun String?.isPdfUrl(): Boolean {
    if (this.isNullOrEmpty()) return false
    if (!this.contains(".")) return false
    val extension = this.getUrlFileFormat()
    return extension?.lowercase() == "pdf"
}

fun String?.isImageUrl(): Boolean {
    if (this.isNullOrEmpty()) return false
    if (!this.contains(".")) return false
    val extension = this.getUrlFileFormat()
    val imageFormats = listOf<String>("png", "jpg", "jpeg")
    for (imageFormat in imageFormats) {
        if (extension?.lowercase() == imageFormat)
            return true
    }
    return false
}

fun String?.getUrlFileFormat(): String? {
    if (this.isNullOrEmpty()) return null
    if (!this.contains(".")) return null
    val extension = this.substringAfterLast(".")
    return extension.ifEmpty { null }

}

fun String?.getUrlFileName(): String? {
    val path = this
    if (path.isNullOrEmpty()) return null
    if (!path.contains(".")) return null

    val fullName = path.substringAfterLast("/")

    return fullName.substringBeforeLast(".")
}

fun String?.toCorrectFolderName(): String? {
    val path = this
    if (path.isNullOrEmpty()) return null
    return path.replace("/", "_").replace(" ", "_")
}

fun String?.getMimeType(): String? {
    if (this.isNullOrEmpty()) return null

    val extension = this.getUrlFileFormat()
    val myMime = MimeTypeMap.getSingleton()
    return myMime.getMimeTypeFromExtension(extension)
}
