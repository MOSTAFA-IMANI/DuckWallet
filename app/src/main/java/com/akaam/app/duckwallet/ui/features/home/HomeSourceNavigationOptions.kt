package com.akaam.app.duckwallet.ui.features.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


 enum class HomeSourceNavigationOptions(val source:String="") {
     Default(),
     FromCreatingWallet("create_wallet"),
     FromImportingWallet("import_wallet");
     companion object {
         fun from(s: String): HomeSourceNavigationOptions? = values().find { it.source == s }
     }
}