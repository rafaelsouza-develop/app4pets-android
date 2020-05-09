package com.appforpets.app4pets.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


open class User(

    @SerializedName("_id")
    val id: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("name")
    val name : String?


)