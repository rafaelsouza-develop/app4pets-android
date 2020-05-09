package com.appforpets.app4pets.domain

import com.appforpets.app4pets.data.local.CredentialsDao

import com.google.gson.JsonSyntaxException


class TokenUseCaseImpl(private val credentialsDao: CredentialsDao): TokenUseCase {



    override fun saveToken(token: String) {
        credentialsDao.save(token)
    }


    override fun getToken(): String? {
        try {
            val credential = getCredentialFromDatabase()
            return if (credential!!.isEmpty()) {
                null
            } else {
                credential
            }
        } catch (jsonSyntaxException: JsonSyntaxException) {
            credentialsDao.remove()
            return null
        }
    }




    private fun getCredentialFromDatabase(): String? {

        return credentialsDao.getCredential()
    }

}