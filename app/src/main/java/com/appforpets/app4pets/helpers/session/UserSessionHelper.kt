package com.appforpets.app4pets.helpers.session

interface UserSessionHelper {

    fun hasUserLogged(): Boolean

    fun logout()
}