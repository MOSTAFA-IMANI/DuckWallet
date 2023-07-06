package com.akaam.app.duckwallet.ui.theme.icon

import androidx.annotation.DrawableRes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource


object DuckWalletIcons {
    val Visibility = Icon.ImageVectorIcon(Icons.Filled.Lock)
    val VisibilityOff = Icon.ImageVectorIcon(Icons.Filled.Person)

//    val Feeds = Icon.ImageVectorIcon(Icons.Filled.Feed)
//    val FeedsBorder = Icon.ImageVectorIcon(Icons.Outlined.Feed)

    val Discover = Icon.ImageVectorIcon(Icons.Filled.Search)
    val DiscoverBorder = Icon.ImageVectorIcon(Icons.Outlined.Search)

    val Notifications = Icon.ImageVectorIcon(Icons.Filled.Notifications)
    val NotificationsBorder = Icon.ImageVectorIcon(Icons.Outlined.Notifications)

    val Profile = Icon.ImageVectorIcon(Icons.Filled.Person)
    val ProfileBorder = Icon.ImageVectorIcon(Icons.Outlined.Person)
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()

    @Composable
    fun ToIcon() {
        when (this) {
            is ImageVectorIcon -> {
                Icon(imageVector = imageVector, contentDescription = "")
            }
            is DrawableResourceIcon -> {
                Icon(painter = painterResource(id = id), contentDescription = "")
            }
        }
    }
}
