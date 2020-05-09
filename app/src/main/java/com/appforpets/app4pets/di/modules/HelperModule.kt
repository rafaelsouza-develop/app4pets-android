package com.appforpets.app4pets.di.modules

import android.content.Context
import com.appforpets.app4pets.data.local.CredentialsDao
import com.appforpets.app4pets.helpers.checkInternetConnection.InternetConnectionHelper
import com.appforpets.app4pets.helpers.checkInternetConnection.InternetConnectionHelperImpl
import com.appforpets.app4pets.helpers.session.UserSessionHelper
import com.appforpets.app4pets.helpers.session.UserSessionHelperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HelperModule {

    @Singleton
    @Provides
    fun provideInternetConnectionHelper(context: Context) :
            InternetConnectionHelper = InternetConnectionHelperImpl(context)

    @Singleton
    @Provides
    fun provideUserSessionHelper(
        credentialsDao: CredentialsDao
    ) : UserSessionHelper = UserSessionHelperImpl(credentialsDao)
}