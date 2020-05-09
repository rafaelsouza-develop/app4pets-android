package com.appforpets.app4pets.data.remote

import com.appforpets.app4pets.data.remote.models.LoginRequest
import com.appforpets.app4pets.data.remote.models.LoginResponse
import com.appforpets.app4pets.data.remote.models.RegisterRequest
import com.appforpets.app4pets.models.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/auth/authenticate")
    fun login(@Body loginRequest: LoginRequest) : Observable<LoginResponse>

    @POST("/auth/register")
    fun register(@Body registerRequest: RegisterRequest) : Observable<User>

}