package com.akaam.app.duckwallet.ui.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.akaam.app.duckwallet.ui.features.adddevice.addNewDeviceScreen
import com.akaam.app.duckwallet.ui.features.adddevice.navigateToAddNewDeviceScreen
import com.akaam.app.duckwallet.ui.features.adressbook.addressBookScreen
import com.akaam.app.duckwallet.ui.features.adressbook.navigateToAddressBook
import com.akaam.app.duckwallet.ui.features.createwallet.createWalletScreen
import com.akaam.app.duckwallet.ui.features.createwallet.navigateToCreateWallet
import com.akaam.app.duckwallet.ui.features.history.historyScreen
import com.akaam.app.duckwallet.ui.features.history.navigateToHistory
import com.akaam.app.duckwallet.ui.features.home.HomeMenuItem
import com.akaam.app.duckwallet.ui.features.home.HomeSourceNavigationOptions
import com.akaam.app.duckwallet.ui.features.home.homeScreen
import com.akaam.app.duckwallet.ui.features.home.navigateToHome
import com.akaam.app.duckwallet.ui.features.importwallet.importWalletScreen
import com.akaam.app.duckwallet.ui.features.importwallet.navigateToImportWallet
import com.akaam.app.duckwallet.ui.features.invite.inviteScreen
import com.akaam.app.duckwallet.ui.features.invite.navigateToInvite
import com.akaam.app.duckwallet.ui.features.login.loginNavigationRoute
import com.akaam.app.duckwallet.ui.features.login.loginScreen
import com.akaam.app.duckwallet.ui.features.login.navigateToLogin
import com.akaam.app.duckwallet.ui.features.welcome.welcomeScreen
import com.akaam.app.duckwallet.ui.features.welcome.navigateToWelcome
import com.akaam.app.duckwallet.ui.features.mnemonic.mnemonicCodeScreen
import com.akaam.app.duckwallet.ui.features.mnemonic.navigateToMnemonicCode
import com.akaam.app.duckwallet.ui.features.pairledger.navigateToPairLedgerScreen
import com.akaam.app.duckwallet.ui.features.pairledger.pairLedgerScreen
import com.akaam.app.duckwallet.ui.features.profile.navigateToProfile
import com.akaam.app.duckwallet.ui.features.profile.profileScreen
import com.akaam.app.duckwallet.ui.features.sort.navigateToSortBy
import com.akaam.app.duckwallet.ui.features.sort.sortByScreen
import com.akaam.app.duckwallet.ui.features.splash.splashNavigationRoute
import com.akaam.app.duckwallet.ui.features.splash.splashScreen
import com.akaam.app.duckwallet.ui.features.stacking.navigateToStaking
import com.akaam.app.duckwallet.ui.features.stacking.stakingScreen
import com.akaam.app.duckwallet.ui.features.verifymnemonic.navigateToVerifyMnemonicCode
import com.akaam.app.duckwallet.ui.features.verifymnemonic.verifyMnemonicCodeScreen
import com.akaam.app.duckwallet.ui.features.watchwallet.navigateToWatchWallet
import com.akaam.app.duckwallet.ui.features.watchwallet.watchWalletScreen

@Composable
fun DuckWalletNavgraph(
    onFailureOccurred: @Composable (Throwable) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    destinationLabel: MutableState<String>,
    screenModifiers: Modifier,

    ) {


    NavHost(
        navController = navController,
        startDestination = splashNavigationRoute
    ) {
        splashScreen(

            navigateToLogin = {
              destinationLabel.value = navController.navigateToLogin(navOptions {
                    popUpTo(splashNavigationRoute) { inclusive = true }
                })
            },
            navigateToWelcome = {
                destinationLabel.value = navController.navigateToWelcome(navOptions {
                    popUpTo(splashNavigationRoute) { inclusive = true }
                })
            },
        navigateToHome ={ destinationLabel.value=navController.navigateToHome() } )
        loginScreen(

            navigateToWelcome = {
                destinationLabel.value = navController.navigateToWelcome(navOptions {
                    popUpTo(loginNavigationRoute) { inclusive = true }
                })
            },
            navigateToRegister = {
                /*   navController.navigateToRegister()*/
            },
            onFailureOccurred = onFailureOccurred
        )
        welcomeScreen(

            navigateToCreateWallet = {
               navController.navigateToCreateWallet(destinationLabel=destinationLabel)
                },
            navigateToImportWallet = { navController.navigateToImportWallet(destinationLabel=destinationLabel) },
            navigateToPairLedger = {destinationLabel.value =navController.navigateToPairLedgerScreen()},
            navigateToWatchWallet = { navController.navigateToWatchWallet(destinationLabel=destinationLabel) },
            onFailureOccurred = onFailureOccurred,
            modifier = screenModifiers
        )
        createWalletScreen(
            modifier = screenModifiers,
            navigateToYourMnemonicCode = {navController.navigateToMnemonicCode(destinationLabel= destinationLabel)}
        )
        mnemonicCodeScreen(
            modifier = screenModifiers,
            navigateToVerifyMnemonicCode = {navController.navigateToVerifyMnemonicCode(destinationLabel=destinationLabel)}
        )
        verifyMnemonicCodeScreen (
            modifier = screenModifiers,
            navigateToHome = {destinationLabel.value =navController.navigateToHome(homeSourceNavigationOptions = HomeSourceNavigationOptions.FromCreatingWallet)})
        importWalletScreen(
            modifier = screenModifiers,
            navigateToHome = {destinationLabel.value =navController.navigateToHome(homeSourceNavigationOptions = HomeSourceNavigationOptions.FromImportingWallet)})
        watchWalletScreen(
            modifier = screenModifiers,
            navigateToHome = {destinationLabel.value =navController.navigateToHome()})
        pairLedgerScreen(
            modifier = screenModifiers,
            navigateToAddNewDevice ={destinationLabel.value =navController.navigateToAddNewDeviceScreen()})
        addNewDeviceScreen(
            modifier = screenModifiers,
            navigateToHome = {destinationLabel.value =navController.navigateToHome()})
        homeScreen(
            navigateToSendToken = {},
            navigateToReceiveToken = {},
            navigateToSwapToken = {},
            navigateToStakeToken = {},
            navigateToBuyToken = {},
            onFailureOccurred = {},
            onMenuItemClick = {item->
                when(item){
                    HomeMenuItem.AboutUs ->{}
                    HomeMenuItem.AddressBook ->{
                        destinationLabel.value =navController.navigateToAddressBook()
                    }
                    HomeMenuItem.Announcements ->{}
                    HomeMenuItem.FriendInvitation ->{
                        destinationLabel.value =navController.navigateToInvite()

                    }
                    HomeMenuItem.HelperCenter ->{}
                    HomeMenuItem.Profile -> {
                        destinationLabel.value =navController.navigateToProfile()
                    }
                    HomeMenuItem.Settings -> {}
                    HomeMenuItem.SortBy -> {
                        destinationLabel.value =navController.navigateToSortBy()
                    }
                    HomeMenuItem.Stacking -> {
                        destinationLabel.value =navController.navigateToStaking()
                    }
                    HomeMenuItem.TransactionHistory -> {
                        destinationLabel.value =navController.navigateToHistory()
                    }
                }
            }
        )
        profileScreen(
            navigateToWalletName={},
            navigateToChangeWallet={},
            navigateToChangePassword={},
            navigateToChangeAvatar={},
            navigateToLockedByDefault={},
            navigateToBackupPK={},
            navigateToBackupMnemonic={},
            navigateToDeleteWallet={},
            modifier = screenModifiers,
            )

        stakingScreen()
        historyScreen()
        sortByScreen(
            sortByNameOnClick = {},
            sortByVolumeOnClick = {},
            sortByDateOnClick = {},
            modifier = screenModifiers,
        )
        addressBookScreen()
        inviteScreen()
    }

}
