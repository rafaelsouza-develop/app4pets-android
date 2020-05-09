package com.appforpets.app4pets.di.modules

import android.content.Context
import com.appforpets.app4pets.data.local.CredentialsDao
import com.appforpets.app4pets.data.local.CredentialsDaoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataLocalModule {

        @Singleton
        @Provides
        fun providesCredentialsDao(context: Context) :
                CredentialsDao = CredentialsDaoImpl(context)
}
