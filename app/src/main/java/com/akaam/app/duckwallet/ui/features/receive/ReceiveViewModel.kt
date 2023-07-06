package com.akaam.app.duckwallet.ui.features.receive

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidmads.library.qrgenearator.QRGSaver
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.akaam.app.duckwallet.ui.util.extension.shareImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class ReceiveViewModel @Inject constructor(
    /*  private val loginUseCase: LoginUseCase,*/
) : ViewModel() {



    private val _uiState = MutableStateFlow<ReceiveUiState>(ReceiveUiState.Nothing)
    val uiState = _uiState.asStateFlow()


    private val _walletAddress = MutableStateFlow<String>("")
    val walletAddress = _walletAddress.asStateFlow()

    init {
        _walletAddress.value = "SampleAddressFromServer"
    }
    fun getReceiveQrcode(screenWidth: Float): QRGEncoder {

        val qrEncoder = QRGEncoder(walletAddress.value, null, QRGContents.Type.TEXT, (screenWidth/2).toInt() )
        qrEncoder.colorBlack = Color.WHITE;
        qrEncoder.colorWhite = Color.BLACK;

        return qrEncoder
    }
    fun saveQr(qrEncoder: QRGEncoder) {
        val savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).path + "/QRCode/";
        val qrgSaver = QRGSaver()
        qrgSaver.save(
            savePath,
            "wallet address",
            qrEncoder.bitmap,
            QRGContents.ImageType.IMAGE_JPEG
        )
    }
    fun shareQr(context: Context,qrCode: QRGEncoder) {

        context.shareImage(bitmap = qrCode.bitmap,",","gf",)
    }

}


