package com.appforpets.app4pets.domain

import com.appforpets.app4pets.data.remote.models.LoginResponse
import com.appforpets.app4pets.models.User
import io.reactivex.Observable

interface LoginUserCase {

    fun login(email: String, password: String)  : Observable<User>
}