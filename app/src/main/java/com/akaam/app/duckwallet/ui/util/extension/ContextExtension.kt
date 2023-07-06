package com.akaam.app.duckwallet.ui.util.extension

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.util.Constants
import java.io.File
import java.io.FileOutputStream

fun Context.copyTextToClipboard(label:String,text:String){
    val clipboardManager =
        this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText(label,text)
    clipboardManager.setPrimaryClip(clipData)

}
fun Context.shareImage(
    bitmap: Bitmap,
    shareTitle :String,
    shareMessage :String,
) {
    try {

        val context = this
        val cachePath = File(context.externalCacheDir, Constants.CACHE_DIRECTORY)
        cachePath.mkdirs()


        val file = File(cachePath, "share.png").also { file ->
            FileOutputStream(file).use { fileOutputStream ->
                bitmap.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    fileOutputStream)
            }
        }.apply {
            deleteOnExit()
        }

        val shareImageFileUri: Uri = file.getFileUri(context)

        val myMime = MimeTypeMap.getSingleton()

        // Create the intent
        val intent = Intent(Intent.ACTION_SEND).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            putExtra(Intent.EXTRA_STREAM, shareImageFileUri)
            putExtra(Intent.EXTRA_TEXT, shareMessage)
            type = myMime.getMimeTypeFromExtension(".png")
        }

        // Initialize the share chooser
        val chooserTitle: String = shareTitle
        val chooser = Intent.createChooser(intent, chooserTitle)
        val resInfoList: List<ResolveInfo> =
            context.packageManager.queryIntentActivities(
                chooser,
                PackageManager.MATCH_DEFAULT_ONLY)
        for (resolveInfo in resInfoList) {
            val packageName: String = resolveInfo.activityInfo.packageName
           /* context.grantUriPermission(packageName,
                Uri.parse(imageUrl),
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)*/
        }

        context.startActivity(chooser)
    } catch (e: Exception) {

    }
}