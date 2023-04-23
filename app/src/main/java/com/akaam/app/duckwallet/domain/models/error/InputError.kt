package com.akaam.app.duckwallet.domain.models.error


data class InputError(
    val InputMessage: String,
) : Throwable()