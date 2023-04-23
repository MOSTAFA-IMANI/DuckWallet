package com.akaam.app.duckwallet.domain.models.error


data class ApiError(
    val code: Int = Int.MIN_VALUE,
    val apiMessage: String,
) : Throwable()