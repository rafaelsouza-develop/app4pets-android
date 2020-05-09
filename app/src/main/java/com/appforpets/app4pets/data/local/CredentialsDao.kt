package com.appforpets.app4pets.data.local

import com.google.gson.JsonSyntaxException

interface CredentialsDao {

    fun remove() : Boolean

    fun save(token: String) : Boolean

    @Throws(JsonSyntaxException::class)
    fun getCredential(): String?
}