package com.akaam.app.duckwallet.domain.models

data class TokenInfo(
     val id:String,
     val name:String,
     val price:String,
     val amount:String,
     val amountInUSD:String,
     val logoUrl:String,
)
