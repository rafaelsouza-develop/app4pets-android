package com.appforpets.app4pets.data.remote.models

import com.appforpets.app4pets.models.User
import com.google.gson.annotations.SerializedName

open class LoginResponse (

    @SerializedName("user")
    val user: User?,

    @SerializedName("token")
    val token: String?
)