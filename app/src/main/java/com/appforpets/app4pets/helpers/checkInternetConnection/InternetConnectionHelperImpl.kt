package com.appforpets.app4pets.helpers.checkInternetConnection

import android.content.Context
import android.net.ConnectivityManager

class InternetConnectionHelperImpl(private val context: Context): InternetConnectionHelper {

    override fun checkInternetConnection(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }
}