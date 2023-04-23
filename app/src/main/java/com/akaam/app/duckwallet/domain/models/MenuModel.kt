package com.akaam.app.duckwallet.domain.models

import android.content.DialogInterface.OnClickListener
import androidx.annotation.IdRes

data class MenuModel(
    @IdRes
    val  textResourceId:Int,
    val onClick: ()->Unit
)
