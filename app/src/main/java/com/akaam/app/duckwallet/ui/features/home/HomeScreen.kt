package com.akaam.app.duckwallet.ui.features.home


import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.akaam.app.duckwallet.R
import com.akaam.app.duckwallet.domain.models.TokenInfo
import com.akaam.app.duckwallet.ui.theme.FlatButton
import com.akaam.app.duckwallet.ui.theme.MainButton
import com.akaam.app.duckwallet.ui.theme.MenuButton

private val topMenuIconSize = 40.dp
private val topMenuArrowSize = 30.dp
private val mainIconSize = 60.dp
private val mainWalletBoxHeight = 200.dp

@Composable
fun HomeRoute(
    onFailureOccurred: @Composable (Throwable) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToSendToken: () -> Unit,
    navigateToReceiveToken: () -> Unit,
    navigateToSwapToken: () -> Unit,
    navigateToStakeToken: () -> Unit,
    navigateToBuyToken: () -> Unit,
    onMenuItemClick: (HomeMenuItem) -> Unit,
    homeSourceNavigationOptions: HomeSourceNavigationOptions?
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val tokenList by viewModel.tokenList.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        navigateToSendToken = navigateToSendToken,
        navigateToReceiveToken = navigateToReceiveToken,
        navigateToSwapToken = navigateToSwapToken,
        navigateToStakeToken = navigateToStakeToken,
        navigateToBuyToken = navigateToBuyToken,
        onFailureOccurred = onFailureOccurred,
        onMenuClick = viewModel::onTopMenuClick,
        tokenList = tokenList,
        onMenuItemClick=onMenuItemClick,
        onDialogDismiss = viewModel::resetUiState,
    )

    if(uiState==HomeUiState.Nothing&&viewModel.isInitCompose){
        when(homeSourceNavigationOptions){
            HomeSourceNavigationOptions.FromCreatingWallet -> {
                viewModel.showCreateDialog()

            }
            HomeSourceNavigationOptions.FromImportingWallet -> {
                viewModel.showImportDialog()
            }
            else->{
                // not first time visit
            }
        }
    }
}


@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onFailureOccurred: @Composable (Throwable) -> Unit,
    navigateToBuyToken: () -> Unit,
    navigateToStakeToken: () -> Unit,
    navigateToSwapToken: () -> Unit,
    navigateToReceiveToken: () -> Unit,
    navigateToSendToken: () -> Unit,
    tokenList: List<TokenInfo>,
    onMenuClick: () -> Unit,
    onMenuItemClick: (HomeMenuItem) -> Unit,
    onDialogDismiss: (Boolean) -> Unit, ) {
    var isMenuShowing by remember {
        mutableStateOf(false)
    }
    var isEntranceDialogShowing by remember {
        mutableStateOf(false)
    }
    var entranceMessage = ""
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        val (background, mainContent, topMenu, bottomButtonList, menuBox) = createRefs()
        val mainModifier = Modifier.padding(horizontal = 9.dp)


        Image(
            painterResource(id = R.drawable.background_main_home),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .alpha(0.9f)
                .constrainAs(background) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)

                }
        )

        MainContent(
            mainModifier.constrainAs(mainContent) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                bottom.linkTo(bottomButtonList.top)
                height = Dimension.fillToConstraints
            },
            tokenList)


        BottomButtonList(
            modifier = mainModifier
                .padding(bottom = 10.dp)
                .constrainAs(bottomButtonList) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    height = Dimension.wrapContent

                },
            navigateToBuyToken = navigateToBuyToken,
            navigateToStakeToken = navigateToStakeToken,
            navigateToSwapToken = navigateToSwapToken,
            navigateToReceiveToken = navigateToReceiveToken,
            navigateToSendToken = navigateToSendToken,
        )
        if (isMenuShowing) {

            Box(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(MaterialTheme.colors.background.copy(alpha = 0.5f))
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = {}
                )) {}
        }
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val maxMenuHeightDp = screenHeight-topMenuIconSize - topMenuArrowSize - 80.dp
        val animatedMenuHeightDp: Dp by animateDpAsState(targetValue = if (isMenuShowing) maxMenuHeightDp else 0.dp)
        val animatedMenuArrowRotationDegree: Float by animateFloatAsState(targetValue = if (isMenuShowing) 90f else 270f)
        TopMenuIndicators(
            Modifier.constrainAs(topMenu) {
                top.linkTo(menuBox.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                height = Dimension.wrapContent

            },
            onMenuClick = onMenuClick,
        rotateDegree =animatedMenuArrowRotationDegree)
        if (isMenuShowing || animatedMenuHeightDp>15.dp) {

            TopMenu(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary.copy(alpha = 0.36f))
                    .constrainAs(menuBox) {
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .height(animatedMenuHeightDp),
                onMenuItemClick = onMenuItemClick)
        }


        AnimatedVisibility(visible =isEntranceDialogShowing ) {
            DialogBoxEntranceCongratulations(onDismiss ={onDialogDismiss.invoke(true)} , descriptionText = entranceMessage)
        }

    }


    when (uiState) {
        HomeUiState.Nothing, HomeUiState.Loading -> {
            isMenuShowing = false
            isEntranceDialogShowing= false
        }

        is HomeUiState.Failure -> {
            onFailureOccurred(uiState.exception)
        }
        HomeUiState.NavToBuy -> {
            navigateToBuyToken.invoke()
        }
        HomeUiState.NavToReceive -> {
            navigateToReceiveToken.invoke()
        }
        HomeUiState.NavToSend -> {
            navigateToSendToken.invoke()
        }
        HomeUiState.NavToStake -> {
            navigateToStakeToken.invoke()
        }
        HomeUiState.NavToSwap -> {
            navigateToSwapToken.invoke()
        }
        HomeUiState.TopMenuIsShowing -> {
            isMenuShowing = true
        }
        HomeUiState.WalletCreated -> {
            isEntranceDialogShowing= true
            entranceMessage = stringResource(id = R.string.wallet_create_message_description)
        }
        HomeUiState.WalletImported -> {
            isEntranceDialogShowing= true
            entranceMessage = stringResource(id = R.string.wallet_import_message_description)
        }
        HomeUiState.FirstTimeVisitTour -> {
            isEntranceDialogShowing= false
            // show tutorial for first time
        }

        else -> {}
    }
}

@Composable
fun TopMenu(
    modifier: Modifier,
    onMenuItemClick: (HomeMenuItem)->Unit
) {
    val blueColor = ButtonDefaults.outlinedButtonColors(
        contentColor = MaterialTheme.colors.background,
        backgroundColor = MaterialTheme.colors.primary)
    val list = listOf(
        HomeMenuItem.Profile,
         HomeMenuItem.Stacking,
         HomeMenuItem.TransactionHistory,
         HomeMenuItem.SortBy,
         HomeMenuItem.AddressBook,
         HomeMenuItem.FriendInvitation,
         HomeMenuItem.Settings,
         HomeMenuItem.Announcements,
         HomeMenuItem.HelperCenter,
         HomeMenuItem.AboutUs,
    )
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier.padding(horizontal = 33.dp, vertical = 17.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {

        itemsIndexed(list) { index, item ->
            MenuButton(onClick = {
                onMenuItemClick(item)
                Toast.makeText(context, "Not Support In this Version : ${ item.route}", Toast.LENGTH_SHORT).show()},
                text = stringResource(id = item.titleResourceId),
                colors = blueColor,
                textAlign = TextAlign.Start)
        }

    }

}



@Composable
fun MainContent(modifier: Modifier, tokenList: List<TokenInfo>) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(start = 0.dp, top = 60.dp, end = 0.dp, bottom = 0.dp),
    ) {
        item {
            MainWalletInfo()
        }
        item {
            MainListButton()
        }
        item {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(28.dp))
                Text(text = stringResource(id = R.string.token_name),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.secondary)
                Text(text = stringResource(id = R.string.token_price),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.secondary)
                Text(text = stringResource(id = R.string.token_amount),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.secondary)
            }
        }
        itemsIndexed(tokenList) { index, item ->
            MainList(tokenList, item, index)
        }
    }


}

@Composable
fun BottomButtonList(
    navigateToBuyToken: () -> Unit,
    navigateToStakeToken: () -> Unit,
    navigateToSwapToken: () -> Unit,
    navigateToReceiveToken: () -> Unit,
    navigateToSendToken: () -> Unit,
    modifier: Modifier,
) {
    val colorStyle = ButtonDefaults.outlinedButtonColors(
        contentColor = Color.White,
        backgroundColor = MaterialTheme.colors.onSurface)
    val buttonModifier = Modifier.wrapContentWidth()

    val textSize = 9.sp
    Row(modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        MainButton(
            modifier = buttonModifier,
            onClick = navigateToSendToken,
            text = stringResource(id = R.string.send_button_title),
            textSize = textSize,
            contentPadding = PaddingValues.Absolute(),
            colors = colorStyle)
        MainButton(onClick = navigateToReceiveToken,
            modifier = buttonModifier,
            text = stringResource(id = R.string.receive_button_title),
            contentPadding = PaddingValues.Absolute(),
            textSize = textSize,
            colors = colorStyle)
        MainButton(onClick = navigateToSwapToken,
            modifier = buttonModifier,
            text = stringResource(id = R.string.swap_button_title),
            contentPadding = PaddingValues.Absolute(),
            textSize = textSize,
            colors = colorStyle)
        MainButton(onClick = navigateToStakeToken,
            modifier = buttonModifier,
            text = stringResource(id = R.string.stake_button_title),
            contentPadding = PaddingValues.Absolute(),
            textSize = textSize,
            colors = colorStyle)
        MainButton(onClick = navigateToBuyToken,
            modifier = buttonModifier,
            text = stringResource(id = R.string.buy_button_title),
            contentPadding = PaddingValues.Absolute(),
            textSize = textSize,
            colors = colorStyle)
    }

}

@Composable
fun MainList(tokenList: List<TokenInfo>, item: TokenInfo, index: Int) {


    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        // TODO: load from web image logo
        Image(
            painter = rememberAsyncImagePainter(item.logoUrl),
            contentDescription = null,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .padding(0.dp, 5.dp),
            )
        Text(text = item.name,
            style = MaterialTheme.typography.overline)
        Text(text = item.price.toString(),
            style = MaterialTheme.typography.overline)
        Text(text = "${item.amount}  =${item.amountInUSD} $",
            style = MaterialTheme.typography.overline)
    }
    if (index < tokenList.lastIndex)
        Divider(
            modifier = Modifier.alpha(0.1f),
            color = MaterialTheme.colors.onSurface)


}

@Composable
fun MainListButton() {

    val buttonModifier = Modifier.padding(5.dp)
    val greenColor = ButtonDefaults.outlinedButtonColors(
        contentColor = MaterialTheme.colors.background,
        backgroundColor = MaterialTheme.colors.secondary)
    val blueColor = ButtonDefaults.outlinedButtonColors(
        contentColor = MaterialTheme.colors.background,
        backgroundColor = MaterialTheme.colors.primary)
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
        FlatButton(
            modifier = buttonModifier,
            onClick = {},
            text = "TOKEN",
            colors = greenColor)
        FlatButton(
            modifier = buttonModifier,
            onClick = {},
            text = "NET",
            colors = blueColor)
        FlatButton(
            modifier = buttonModifier,
            onClick = {},
            text = "ADD TOKEN",
            colors = blueColor)
    }

}

@Composable
fun MainWalletInfo() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(mainWalletBoxHeight)
            .padding(0.dp, 0.dp)
//            .shadow(8.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colors.secondary)
            .padding(10.dp),


        ) {

        Image(
            painter = painterResource(id = R.drawable.ic_mail),
            contentDescription = null,
            modifier = Modifier
                .width(mainIconSize)
                .height(mainIconSize)
                .align(Alignment.TopEnd),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.background))
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier
                .width(mainIconSize)
                .height(mainIconSize)
                .align(Alignment.CenterEnd),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.background))

        Image(
            painter = painterResource(id = R.drawable.ic_add_wallet),
            contentDescription = null,
            modifier = Modifier
                .width(mainIconSize)
                .height(mainIconSize)
                .align(Alignment.BottomEnd),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.background))
        Row(
            modifier = Modifier.align(Alignment.TopStart),
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(text = "0.00",
                color = MaterialTheme.colors.background,
                style = MaterialTheme.typography.h1)
            Text(text = "DGOLD",
                color = MaterialTheme.colors.background,
                style = MaterialTheme.typography.body1)
        }
        Text(modifier = Modifier.align(Alignment.CenterStart),
            text = "0.00  $",
            color = MaterialTheme.colors.background,
            style = MaterialTheme.typography.h3)

        Text(modifier = Modifier.align(Alignment.BottomStart),
            text = "hjsagdhagsdhajasd \uD83D\uDCCB",
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body2)
    }
}


@Composable
fun TopMenuIndicators(
    modifier: Modifier,
    onMenuClick: () -> Unit,
    rotateDegree: Float
) {
    Column(
        modifier = modifier.clickable(
            interactionSource = MutableInteractionSource(),
            indication = null,
            onClick = onMenuClick
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(32.dp, 0.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.background_home_top_menu),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            )
            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {

                Image(
                    painter = painterResource(id = R.drawable.ic_home_topmenu_eye),
                    contentDescription = null,
                    modifier = Modifier
                        .width(topMenuIconSize)
                        .height(topMenuIconSize),
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_home_topmenu_man),
                    contentDescription = null,
                    modifier = Modifier
                        .width(topMenuIconSize)
                        .height(topMenuIconSize),
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_home_topmenu_lock),
                    contentDescription = null,
                    modifier = Modifier
                        .width(topMenuIconSize)
                        .height(topMenuIconSize),
                )
            }


        }

        val arrowModifier = Modifier
            .width(topMenuArrowSize)
            .height(topMenuArrowSize)
            .rotate(rotateDegree)


        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = arrowModifier,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
        )
    }

}

@Composable
fun DialogBoxEntranceCongratulations(
    cornerRadius: Dp = 12.dp,
    titleTextStyle: TextStyle = MaterialTheme.typography.h3,
    messageTextStyle: TextStyle = MaterialTheme.typography.body1,
    buttonTextStyle: TextStyle = MaterialTheme.typography.button,
    titleText : String = stringResource(id = R.string.congratulations),
    imageDrawableId: Int = R.drawable.ic_dialog_happy_duck,
    descriptionText : String = "",
    onDismiss: () -> Unit
) {



    val paddingValue = 12.dp


    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(size = cornerRadius),

        ) {

            Column(modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .heightIn(0.dp, 500.dp)) {

                Column(modifier = Modifier
                    .padding(
                        top = paddingValue,
                        start = paddingValue,
                        end = paddingValue,
                        bottom = 0.dp
                    )
                    .clip(RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius))
                    .background(MaterialTheme.colors.primaryVariant),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = titleText,
                        style = titleTextStyle,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 12.dp)
                            .alpha(0.5f),
                        text = descriptionText,
                        style = messageTextStyle,
                        textAlign = TextAlign.Center
                    )
                    Image(
                        painter = painterResource(id = imageDrawableId),
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.BottomCenter,
                        contentDescription =titleText )
                }

                MainButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp, vertical = 10.dp),
                    onClick = onDismiss,
                    text = stringResource(id = R.string.done_button_title),isSecondory =true )
            }

        }

    }
}


