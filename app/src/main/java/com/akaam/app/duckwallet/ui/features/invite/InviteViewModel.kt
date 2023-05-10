package com.akaam.app.duckwallet.ui.features.invite

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class InviteViewModel @Inject constructor(
    /*  private val loginUseCase: LoginUseCase,*/
) : ViewModel() {

    private val _uiState = MutableStateFlow<InviteUiState>(InviteUiState.Nothing)
    val uiState = _uiState.asStateFlow()

    private val _credit = MutableStateFlow<Int>(0)
    val credit = _credit.asStateFlow()

    private val _invitedCount = MutableStateFlow<Int>(0)
    val invitedCount = _invitedCount.asStateFlow()

    private val _invitationCode = MutableStateFlow<String>("invationcode1getting2from3server")
    val invitationCode = _invitationCode.asStateFlow()




}


