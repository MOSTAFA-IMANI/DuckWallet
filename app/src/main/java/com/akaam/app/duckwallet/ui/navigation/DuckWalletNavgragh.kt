package com.akaam.app.duckwallet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.akaam.app.duckwallet.ui.features.addwallet.addWalletScreen
import com.akaam.app.duckwallet.ui.features.addwallet.navigateToAddWallet
import com.akaam.app.duckwallet.ui.features.pairledger.adddevice.addNewDeviceScreen
import com.akaam.app.duckwallet.ui.features.pairledger.adddevice.navigateToAddNewDeviceScreen
import com.akaam.app.duckwallet.ui.features.adressbook.addressBookScreen
import com.akaam.app.duckwallet.ui.features.adressbook.navigateToAddressBook
import com.akaam.app.duckwallet.ui.features.buy.buyScreen
import com.akaam.app.duckwallet.ui.features.buy.navigateToBuy
import com.akaam.app.duckwallet.ui.features.createwallet.root.createWalletScreen
import com.akaam.app.duckwallet.ui.features.createwallet.root.navigateToCreateWallet
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
import com.akaam.app.duckwallet.ui.features.createwallet.mnemonic.mnemonicCodeScreen
import com.akaam.app.duckwallet.ui.features.createwallet.mnemonic.navigateToMnemonicCode
import com.akaam.app.duckwallet.ui.features.pairledger.root.navigateToPairLedgerScreen
import com.akaam.app.duckwallet.ui.features.pairledger.root.pairLedgerScreen
import com.akaam.app.duckwallet.ui.features.profile.navigateToProfile
import com.akaam.app.duckwallet.ui.features.profile.profileScreen
import com.akaam.app.duckwallet.ui.features.receive.navigateToReceive
import com.akaam.app.duckwallet.ui.features.receive.receiveScreen
import com.akaam.app.duckwallet.ui.features.send.address.navigateToSendAddress
import com.akaam.app.duckwallet.ui.features.send.address.sendAddressScreen
import com.akaam.app.duckwallet.ui.features.send.confirm.navigateToSendConfirm
import com.akaam.app.duckwallet.ui.features.send.confirm.sendConfirmScreen
import com.akaam.app.duckwallet.ui.features.send.selection.navigateToSendSelection
import com.akaam.app.duckwallet.ui.features.send.selection.sendSelectionScreen
import com.akaam.app.duckwallet.ui.features.sort.navigateToSortBy
import com.akaam.app.duckwallet.ui.features.sort.sortByScreen
import com.akaam.app.duckwallet.ui.features.splash.splashNavigationRoute
import com.akaam.app.duckwallet.ui.features.splash.splashScreen
import com.akaam.app.duckwallet.ui.features.stacking.navigateToStaking
import com.akaam.app.duckwallet.ui.features.stacking.stakingScreen
import com.akaam.app.duckwallet.ui.features.stake.selection.navigateToStakeSelection
import com.akaam.app.duckwallet.ui.features.stake.selection.stakeSelectionScreen
import com.akaam.app.duckwallet.ui.features.swap.confirm.navigateToSwapConfirm
import com.akaam.app.duckwallet.ui.features.swap.confirm.swapConfirmScreen
import com.akaam.app.duckwallet.ui.features.swap.selection.navigateToSwapSelection
import com.akaam.app.duckwallet.ui.features.swap.selection.swapSelectionScreen
import com.akaam.app.duckwallet.ui.features.createwallet.verifymnemonic.navigateToVerifyMnemonicCode
import com.akaam.app.duckwallet.ui.features.createwallet.verifymnemonic.verifyMnemonicCodeScreen
import com.akaam.app.duckwallet.ui.features.notification.navigateToNotification
import com.akaam.app.duckwallet.ui.features.notification.notificationScreen
import com.akaam.app.duckwallet.ui.features.password.changePasswordScreen
import com.akaam.app.duckwallet.ui.features.password.navigateToChangePassword
import com.akaam.app.duckwallet.ui.features.search.navigateToSearch
import com.akaam.app.duckwallet.ui.features.search.searchScreen
import com.akaam.app.duckwallet.ui.features.walletname.navigateToWalletName
import com.akaam.app.duckwallet.ui.features.walletname.walletNameScreen
import com.akaam.app.duckwallet.ui.features.watchwallet.navigateToWatchWallet
import com.akaam.app.duckwallet.ui.features.watchwallet.watchWalletScreen
import com.akaam.app.duckwallet.ui.features.welcome.navigateToWelcome
import com.akaam.app.duckwallet.ui.features.welcome.welcomeScreen

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
            navigateToSendToken = {
                destinationLabel.value = navController.navigateToSendAddress()
            },
            navigateToAddWallet = {navController.navigateToAddWallet()},
            navigateToSearch = {navController.navigateToSearch()},
            navigateToNotification ={navController.navigateToNotification()},
            navigateToReceiveToken = { destinationLabel.value = navController.navigateToReceive() },
            navigateToSwapToken = {
                destinationLabel.value = navController.navigateToSwapSelection()
            },
            navigateToStakeToken = {destinationLabel.value = navController.navigateToStakeSelection()},
            navigateToBuyToken = { destinationLabel.value = navController.navigateToBuy() },
            onFailureOccurred = {},
            navigateToLock = {},
            navigateToChangeAvatar = {},
            onMenuItemClick = { item ->
                when (item) {
                    HomeMenuItem.AboutUs -> {}
                    HomeMenuItem.AddressBook -> {
                        destinationLabel.value = navController.navigateToAddressBook()
                    }

                    HomeMenuItem.Announcements -> {}
                    HomeMenuItem.FriendInvitation -> {
                        destinationLabel.value = navController.navigateToInvite()

                    }

                    HomeMenuItem.HelperCenter -> {}
                    HomeMenuItem.Profile -> {
                        destinationLabel.value = navController.navigateToProfile()
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
            navigateToWalletName={navController.navigateToWalletName()},
            navigateToChangeWallet={},
            navigateToChangePassword={navController.navigateToChangePassword()},
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

        buyScreen(modifier = screenModifiers)
        swapSelectionScreen(
            modifier = screenModifiers,
            onNextStepClick = { destinationLabel.value = navController.navigateToSwapConfirm() })
        swapConfirmScreen(modifier = screenModifiers, onNextStepClick = {})
        receiveScreen(modifier = screenModifiers, onNextStepClick = {})
        sendAddressScreen(
            modifier = screenModifiers,
            onNextStepClick = { destinationLabel.value = navController.navigateToSendSelection() },
            onAddressBookClick = { destinationLabel.value = navController.navigateToAddressBook() },
            onMyAccountsClick = {},
            onRecentClick = {},
        )
        sendSelectionScreen(
            modifier = screenModifiers,
            onNextStepClick = {destinationLabel.value = navController.navigateToSendConfirm()},
        )
        sendConfirmScreen(
            modifier = screenModifiers,
            onNextStepClick = {},
        )
        stakeSelectionScreen (
            modifier = screenModifiers,
            onNextStepClick = {},
        )
        notificationScreen()
        searchScreen()
        addWalletScreen(

            navigateToCreateWallet = {
                navController.navigateToCreateWallet(destinationLabel=destinationLabel)
            },
            navigateToImportWallet = { navController.navigateToImportWallet(destinationLabel=destinationLabel) },
            navigateToPairLedger = {destinationLabel.value =navController.navigateToPairLedgerScreen()},
            navigateToWatchWallet = { navController.navigateToWatchWallet(destinationLabel=destinationLabel) },
            onFailureOccurred = onFailureOccurred,
            modifier = screenModifiers
        )
        walletNameScreen()
        changePasswordScreen()
    }

}
