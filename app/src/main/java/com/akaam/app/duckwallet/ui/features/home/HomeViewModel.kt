package com.akaam.app.duckwallet.ui.features.home

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.akaam.app.duckwallet.domain.models.MenuModel
import com.akaam.app.duckwallet.domain.models.TokenInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    /*  private val loginUseCase: LoginUseCase,*/
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Nothing)
    val uiState = _uiState.asStateFlow()

    private val _tokenList :MutableStateFlow<List<TokenInfo>> = MutableStateFlow(listOf())
    val tokenList = _tokenList.asStateFlow()


    init {
        fillTokenList()

    }



    private fun fillTokenList() {
        val tempList = ArrayList<TokenInfo>()

        tempList.add(TokenInfo("1","Token name","0.00","0.00","0.00 $","https://assets.coingecko.com/coins/images/1/thumb/bitcoin.png?1547033579"))
        tempList.add(TokenInfo("2","Token name","0.00","0.00","0.00 $","https://assets.coingecko.com/coins/images/279/thumb/ethereum.png?1595348880"))
        tempList.add(TokenInfo("3","Token name","0.00","0.00","0.00 $","https://bitholla.s3.ap-northeast-2.amazonaws.com/exchange/hapycoin/DGOLD.png"))
        tempList.add(TokenInfo("4","Token name","0.00","0.00","0.00 $","https://assets.coingecko.com/coins/images/5/thumb/dogecoin.png?1547792256"))

        _tokenList.value = tempList

    }

    fun sendToken() {
        _uiState.value = HomeUiState.NavToSend
    }

    fun receiveToken() {
        _uiState.value = HomeUiState.NavToReceive
    }

    fun swapToken() {
        _uiState.value = HomeUiState.NavToSwap
    }

    fun stakeToken() {
        _uiState.value = HomeUiState.NavToStake
    }

    fun buyToken() {
        _uiState.value = HomeUiState.NavToBuy
    }
    fun onTopMenuClick(){
        if(uiState.value==HomeUiState.TopMenuIsShowing){
            resetUiState()
        }
        else{
            _uiState.value = HomeUiState.TopMenuIsShowing
        }
    }

    fun resetUiState() {
        _uiState.value = HomeUiState.Nothing
    }

    fun showCreateDialog() {
        _uiState.value = HomeUiState.WalletCreated
    }

    fun showImportDialog() {
        _uiState.value = HomeUiState.WalletImported
    }
}


