package com.akaam.app.duckwallet.domain.models

data class TokenInfo(
     val id:String,
     val name:String,
     val price:Double,
     val amount:Double,
     val amountInUSD:Double,
     val logoUrl:String,
     val currencyFeeInUSD:Double = 1.0,
)
