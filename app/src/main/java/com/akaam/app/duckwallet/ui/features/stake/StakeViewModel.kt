package com.akaam.app.duckwallet.ui.features.stake

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.akaam.app.duckwallet.domain.models.TokenInfo
import com.akaam.app.duckwallet.ui.features.send.address.SendAddressUiState
import com.akaam.app.duckwallet.ui.features.send.confirm.SendConfirmUiState
import com.akaam.app.duckwallet.ui.features.send.selection.SendSelectionUiState
import com.akaam.app.duckwallet.ui.features.stake.selection.StakeSelectionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StakeViewModel @Inject constructor(
    /*  private val loginUseCase: LoginUseCase,*/
) : ViewModel() {



    private val _uiStateSelection = MutableStateFlow<StakeSelectionUiState>(StakeSelectionUiState.Nothing)
    val uiStateSelection = _uiStateSelection.asStateFlow()

    private val _uiStateConfirm = MutableStateFlow<SendConfirmUiState>(SendConfirmUiState.Nothing)
    val uiStateConfirm = _uiStateConfirm.asStateFlow()


    var receivingAccountAddress by mutableStateOf("")
        private set

    private var selectedTokenInfo: TokenInfo? by mutableStateOf(null)

    var sendingTokenAmount by mutableStateOf(0.0)
        private set

    var transferNote by mutableStateOf("")
        private set

    var sendingTokenAmountUSD by mutableStateOf("= 0.0 $")
        private set

   private val _tokenList: MutableStateFlow<List<TokenInfo>> = MutableStateFlow(listOf())
    val tokenList = _tokenList.asStateFlow()

    init {
        fillTokenList()
    }
    fun fillTokenList() {
        val tempList = ArrayList<TokenInfo>()

        tempList.add(
            TokenInfo(
                "1",
                "Token name1",
                0.00,
                0.00,
                0.00,
                "https://cryptoicons.org/api/icon/btc/200"
            )
        )
        tempList.add(
            TokenInfo(
                "2",
                "Token name2",
                0.00,
                0.00,
                0.00,
                "https://cryptoicons.org/api/icon/ada/200"
            )
        )
        tempList.add(
            TokenInfo(
                "3",
                "Token name3",
                0.00,
                0.00,
                0.00,
                "https://cryptoicons.org/api/icon/eth/200"
            )
        )
        tempList.add(
            TokenInfo(
                "4",
                "Token name4",
                0.00,
                0.00,
                0.00,
                "https://cryptoicons.org/api/icon/dgd/200"
            )
        )

        _tokenList.value = tempList

    }
    fun updateAmount(input:String) {
        sendingTokenAmount = input.toDoubleOrNull()?:0.0
        selectedTokenInfo?.let {
            sendingTokenAmountUSD = " = ${(sendingTokenAmount * it.currencyFeeInUSD)} $"
        }
    }
    fun getSendingTokenInfo(): TokenInfo? {
        return  selectedTokenInfo
    }
    fun setSendingTokenInfo(tokenInfo: TokenInfo) {
        selectedTokenInfo = tokenInfo
    }
    fun updateReceivingAccountAddress(input:String) {
        receivingAccountAddress = input

    }


}




