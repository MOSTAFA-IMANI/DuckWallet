package com.akaam.app.duckwallet.ui.features.swap

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.akaam.app.duckwallet.domain.models.TokenInfo
import com.akaam.app.duckwallet.ui.features.swap.confirm.SwapConfirmUiState
import com.akaam.app.duckwallet.ui.features.swap.selection.SwapSelectionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SwapViewModel @Inject constructor(
    /*  private val loginUseCase: LoginUseCase,*/
) : ViewModel() {


    private val _uiStateSelection =
        MutableStateFlow<SwapSelectionUiState>(SwapSelectionUiState.Nothing)
    val selectionUiState = _uiStateSelection.asStateFlow()


    private val _uiStateConfirm = MutableStateFlow<SwapConfirmUiState>(SwapConfirmUiState.Nothing)
    val confirmUiState = _uiStateConfirm.asStateFlow()

    private var originTokenInfo: TokenInfo? by mutableStateOf(null)
    private var distinctionTokenInfo: TokenInfo? by mutableStateOf(null)

    var buyingTokenAmount by mutableStateOf(0.0)
        private set

    var buyingTokenAmountUSD by mutableStateOf("= 0.0 $")
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

    fun updateAmount(input: String) {
        buyingTokenAmount = input.toDoubleOrNull() ?: 0.0
        originTokenInfo?.let {
            buyingTokenAmountUSD = " = ${(buyingTokenAmount * it.currencyFeeInUSD)} $"
        }
    }

    fun getOriginInfo(): TokenInfo? {
        return originTokenInfo
    }

    fun getDestinationInfo(): TokenInfo? {
        return distinctionTokenInfo
    }

    fun setOriginInfo(tokenInfo: TokenInfo) {
        originTokenInfo = tokenInfo
    }

    fun setDestinationInfo(tokenInfo: TokenInfo) {
        distinctionTokenInfo = tokenInfo
    }
}


