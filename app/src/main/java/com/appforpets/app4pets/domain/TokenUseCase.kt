package com.appforpets.app4pets.domain

import io.reactivex.Observable

interface TokenUseCase {

    fun getToken(): String?

    fun saveToken(token:String)
}