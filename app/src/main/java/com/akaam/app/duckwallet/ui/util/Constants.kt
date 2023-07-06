package com.akaam.app.duckwallet.ui.util

object Constants {

     const val  MNEMONIC_LIST_SIZE = 12
     fun emptyListForCode(size: Int = MNEMONIC_LIST_SIZE): List<String> {
          val tempList= mutableListOf<String>()
          repeat(size){
               tempList.add(" ")
          }
          return tempList
     }
     const val CACHE_DIRECTORY = "our_docs/"

}