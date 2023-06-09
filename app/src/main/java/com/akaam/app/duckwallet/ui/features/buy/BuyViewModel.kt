package com.akaam.app.duckwallet.ui.features.buy

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.akaam.app.duckwallet.domain.models.ThirdPartyProvider
import com.akaam.app.duckwallet.domain.models.TokenInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BuyViewModel @Inject constructor(
    /*  private val loginUseCase: LoginUseCase,*/
) : ViewModel() {


    private val _uiState = MutableStateFlow<BuyUiState>(BuyUiState.Nothing)
    val uiState = _uiState.asStateFlow()

    private var selectedTokenInfo: TokenInfo? by mutableStateOf(null)

    var buyingTokenAmount by mutableStateOf(0.0)
        private set

    var buyingTokenAmountUSD by mutableStateOf("= 0.0 $")
        private set

    private val _tokenList: MutableStateFlow<List<TokenInfo>> = MutableStateFlow(listOf())
    val tokenList = _tokenList.asStateFlow()

    private val _thirdPartyBuyingProvider = mutableStateListOf<ThirdPartyProvider>()
    val thirdPartyBuyingProvider: List<ThirdPartyProvider> = _thirdPartyBuyingProvider


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

    init {
        fillTokenList()
        _thirdPartyBuyingProvider.add(
            ThirdPartyProvider("idServer","ThirdPartyName","Third Party Provider","https://cryptoicons.org/api/icon/cix/200"))
    }
    fun updateAmount(input:String) {
        buyingTokenAmount = input.toDoubleOrNull()?:0.0
        selectedTokenInfo?.let {
            buyingTokenAmountUSD = " = ${(buyingTokenAmount * it.currencyFeeInUSD)} $"
        }
    }
    fun getBuyingTokenInfo(): TokenInfo? {
        return  selectedTokenInfo
    }
    fun setBuyingTokenInfo(tokenInfo: TokenInfo) {
        selectedTokenInfo = tokenInfo
    }
}


