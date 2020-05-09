package com.appforpets.app4pets.helpers.session

import com.appforpets.app4pets.data.local.CredentialsDao

class UserSessionHelperImpl(
    private val credentialsDao: CredentialsDao
): UserSessionHelper {

    override fun logout() {
        credentialsDao.remove()
    }

    override fun hasUserLogged(): Boolean {
        return credentialsDao.getCredential() != null
    }
}