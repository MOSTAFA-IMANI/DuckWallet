package com.akaam.app.duckwallet.ui.features.swap

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.akaam.app.duckwallet.domain.models.TokenInfo
import com.akaam.app.duckwallet.ui.features.swap.selection.SwapSelectionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SwapViewModel @Inject constructor(
    /*  private val loginUseCase: LoginUseCase,*/
) : ViewModel() {


    private val _uiState = MutableStateFlow<SwapSelectionUiState>(SwapSelectionUiState.Nothing)
    val uiState = _uiState.asStateFlow()

    private var originTokenInfo: TokenInfo? by mutableStateOf(null)
    private var distinctionTokenInfo: TokenInfo? by mutableStateOf(null)

    var buyingTokenAmount by mutableStateOf(0.0)
        private set

    var buyingTokenAmountUSD by mutableStateOf("= 0.0 $")
        private set

    init {

         }
    fun updateAmount(input:String) {
        buyingTokenAmount = input.toDoubleOrNull()?:0.0
        originTokenInfo?.let {
            buyingTokenAmountUSD = " = ${(buyingTokenAmount * it.currencyFeeInUSD)} $"
        }
    }
    fun getOriginInfo(): TokenInfo? {
        return  originTokenInfo
    }
    fun getDestinationInfo(): TokenInfo? {
        return  distinctionTokenInfo
    }
}


