package com.akaam.app.duckwallet.ui.features.createwallet.mnemonic

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.graphics.Bitmap
import android.graphics.Point
import android.view.Display
import android.view.WindowManager
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akaam.app.duckwallet.ui.util.extension.copyTextToClipboard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MnemonicViewModel @Inject constructor(

) : ViewModel() {
    //    val state: StateFlow<MnemonicCodeUiState> = MnemonicCodeUiState.Loading
    private val _uiState: MutableStateFlow<MnemonicCodeUiState> =
        MutableStateFlow(MnemonicCodeUiState.Loading)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<MnemonicCodeUiState> = _uiState.asStateFlow()



     val mnemonicCodeList :MutableStateFlow<List<String>> = MutableStateFlow(listOf())

    init {

        val serverListOfMnemonicCode = mutableListOf<String>()
        repeat(12){
            serverListOfMnemonicCode.add("word$it")
        }
        mnemonicCodeList.value =serverListOfMnemonicCode
        resetUiState()
    }

    fun createWallet() {

        // TODO: Call for Api
        viewModelScope.launch {

            _uiState.value = MnemonicCodeUiState.NavigateToVerifyMnemonic
        }
    }
    fun copyMnemonicListCode(context:Context){
        context.copyTextToClipboard("Mnemonic Code:",getMnemonicPlaneText())
    }
    fun generateQrCode(context:Context){
        val windowManager: WindowManager = context.getSystemService(WINDOW_SERVICE) as WindowManager

        val display: Display = windowManager.defaultDisplay

        val point: Point = Point()
        display.getSize(point)

        val width = point.x
        val height = point.y

        var dimen = if (width < height) width else height
        dimen = dimen * 3 / 4


       val qrEncoder = QRGEncoder(getMnemonicPlaneText(), null, QRGContents.Type.TEXT, dimen)

        try {
           val bitmap = qrEncoder.bitmap;
            _uiState.value = MnemonicCodeUiState.QrCodeHasGenerated(bitmap)
//            qrImage.setImageBitmap(bitmap);
        } catch (e: Exception) {

            e.printStackTrace()
        }

    }

    private fun getMnemonicPlaneText(): String {
        return mnemonicCodeList.value.joinToString(separator = " ", )
    }

    public fun resetUiState() {
        _uiState.value = MnemonicCodeUiState.Nothing
    }


}
