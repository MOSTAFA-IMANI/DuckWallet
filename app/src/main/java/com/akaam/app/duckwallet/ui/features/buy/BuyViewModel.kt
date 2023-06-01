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


    private val _thirdPartyBuyingProvider = mutableStateListOf<ThirdPartyProvider>()
    val thirdPartyBuyingProvider: List<ThirdPartyProvider> = _thirdPartyBuyingProvider

    init {
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
}


