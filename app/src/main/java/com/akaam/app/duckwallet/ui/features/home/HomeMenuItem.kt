package com.akaam.app.duckwallet.ui.features.home

import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.ui.features.profile.profileNavigationRoute


sealed class HomeMenuItem(var route: String, var titleResourceId: Int)
{
    object Profile : HomeMenuItem(profileNavigationRoute, R.string.title_profile_route)
    object Stacking : HomeMenuItem("stacking_route", R.string.title_stacking_route)
    object TransactionHistory : HomeMenuItem("transaction_history_route", R.string.title_transaction_history_route)
    object SortBy : HomeMenuItem("sort_by_route", R.string.title_sort_by_route)
    object AddressBook : HomeMenuItem("address_book_route", R.string.title_address_book_route)
    object FriendInvitation : HomeMenuItem("friend_invitation_route",R.string.title_friend_invitation_route )
    object Settings : HomeMenuItem("settings_route", R.string.title_settings_route)
    object Announcements : HomeMenuItem("announcements_route", R.string.title_announcements_route)
    object HelperCenter : HomeMenuItem("helper_center_route",R.string.title_helper_center_route )
    object AboutUs : HomeMenuItem("about_us_route", R.string.title_about_us_route)
}
