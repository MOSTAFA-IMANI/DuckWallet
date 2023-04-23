package com.akaam.app.duckwallet.ui.features.verifymnemonic

import androidx.lifecycle.ViewModel
import com.akaam.app.duckwallet.ui.util.Constants.MNEMONIC_LIST_SIZE
import com.akaam.app.duckwallet.ui.util.Constants.emptyListForCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


@HiltViewModel
class VerifyMnemonicViewModel @Inject constructor(

) : ViewModel() {

    //    val state: StateFlow<MnemonicCodeUiState> = MnemonicCodeUiState.Loading
    private val _uiState: MutableStateFlow<VerifyMnemonicCodeUiState> =
        MutableStateFlow(VerifyMnemonicCodeUiState.Loading)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<VerifyMnemonicCodeUiState> = _uiState.asStateFlow()



     val selectedMnemonicCodeList :MutableStateFlow<List<String>> = MutableStateFlow(listOf())
     val inputMnemonicCodeList :MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    private val correctCodeList:List<String>

    init {

        val serverListOfMnemonicCode = mutableListOf<String>()

        repeat(MNEMONIC_LIST_SIZE){
            serverListOfMnemonicCode.add("Server$it")
        }
        correctCodeList= serverListOfMnemonicCode

        inputMnemonicCodeList.value = shuffledOrderList(correctCodeList)
        inputMnemonicCodeList.value =serverListOfMnemonicCode
        selectedMnemonicCodeList.value = emptyListForCode(MNEMONIC_LIST_SIZE)
        resetUiState()
    }
/*
    private fun emptyListForCode(size: Int): List<String> {
        val tempList= mutableListOf<String>()
        repeat(size){
            tempList.add(" ")
        }
        return tempList
    }*/

    fun checkInsertedCode() {

        // TODO: Call for Api
        if (inputMnemonicCodeList.value.isNotEmpty()){
            _uiState.value = VerifyMnemonicCodeUiState.EmptyInputException
            return
        }
        if (isCorrectOrderForList()){
            _uiState.value = VerifyMnemonicCodeUiState.NavigateToHome

        }
        else{
            _uiState.value = VerifyMnemonicCodeUiState.IncorrectInputException

        }
    }

    private fun isCorrectOrderForList(): Boolean {
        try {
            for (i in correctCodeList.indices){
                if(correctCodeList[i]!=selectedMnemonicCodeList.value[i])
                    return false
            }
        }catch (e:Exception){
            return false
        }

        return true
    }

    fun onItemClicked(content:String,index:Int){
        val isRemoved = removeFromSelectItems(content,index)
     if(isRemoved){
         addToSelectedList(content,index)
     }
    }

    private fun removeFromSelectItems(content: String, index: Int): Boolean {
        val currentInputList =ArrayList<String>( inputMnemonicCodeList.value)
        val isRemoved = currentInputList.remove(content)
        inputMnemonicCodeList.value = currentInputList
        return isRemoved
    }

    private fun addToSelectedList(content: String, index: Int) {
        val currentSelectedList =ArrayList<String>( selectedMnemonicCodeList.value)
       val firstEmptyPlace = currentSelectedList.indexOfFirst { it.isBlank() }
       currentSelectedList[firstEmptyPlace] = content
        selectedMnemonicCodeList.value = currentSelectedList
    }


    fun resetUiState(isResetInsertedValue:Boolean=false) {

        _uiState.value = VerifyMnemonicCodeUiState.Nothing
        if (isResetInsertedValue){
            selectedMnemonicCodeList.value = emptyListForCode(size = MNEMONIC_LIST_SIZE)
            inputMnemonicCodeList.value = shuffledOrderList(correctCodeList)
        }

    }

    private fun shuffledOrderList(correctCodeList: List<String>): List<String> {
        val temp = ArrayList<String>(correctCodeList)
        temp.shuffle(Random())
        return temp
    }


}
