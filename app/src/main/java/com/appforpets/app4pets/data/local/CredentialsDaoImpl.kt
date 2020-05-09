package com.appforpets.app4pets.data.local

import android.content.Context


class CredentialsDaoImpl(context: Context) : CredentialsDao {

    companion object {
        const val CREDENTIAL_PREFERENCE_NAME = "credential_shared_pref"
        const val CREDENTIAL_PREFERENCE_KEY = "credential"
        const val CREDENTIAL_ACCESS_TOKEN_KEY = "credential_access_token"
        const val CREDENTIAL_REFRESH_TOKEN_KEY = "credential_refresh_token"
        const val CREDENTIAL_EXPIRE_DATE_KEY = "credential_expire_date"

    }

    private val sharedPreference = context.getSharedPreferences(CREDENTIAL_PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )

    override fun save(token: String): Boolean {

        val edit = sharedPreference.edit()

        if (!token.isNullOrEmpty()) {
            edit.putString(CREDENTIAL_ACCESS_TOKEN_KEY, token)
        }

        return edit.commit()
    }

    override fun getCredential(): String? {

        val accessToken = sharedPreference.getString(CREDENTIAL_ACCESS_TOKEN_KEY, null)


        return accessToken
    }

    override fun remove(): Boolean {

        return sharedPreference
            .edit()
            .remove(CREDENTIAL_ACCESS_TOKEN_KEY)
            .commit()
    }



}