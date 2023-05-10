package com.akaam.app.duckwallet.ui.util.extension

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

fun Context.copyTextToClipboard(label:String,text:String){
    val clipboardManager =
        this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText(label,text)
    clipboardManager.setPrimaryClip(clipData)

}