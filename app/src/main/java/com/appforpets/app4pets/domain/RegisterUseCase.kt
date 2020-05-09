package com.appforpets.app4pets.domain

import io.reactivex.Observable

interface RegisterUseCase {

    fun createAccount(email: String, name: String, password: String) : Observable<Boolean>
}