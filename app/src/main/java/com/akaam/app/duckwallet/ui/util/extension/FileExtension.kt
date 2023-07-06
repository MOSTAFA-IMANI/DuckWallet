package com.akaam.app.duckwallet.ui.util.extension

import android.app.Activity
import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.akaam.app.duckwallet.BuildConfig
import java.io.File

fun File.getFileUri(context: Context): Uri {
    return FileProvider.getUriForFile(context,
        BuildConfig.APPLICATION_ID + ".provider", this)
}