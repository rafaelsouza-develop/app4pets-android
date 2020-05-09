package com.appforpets.app4pets.di.modules

import com.appforpets.app4pets.data.local.CredentialsDao
import com.appforpets.app4pets.data.remote.AuthService
import com.appforpets.app4pets.domain.LoginUserCase
import com.appforpets.app4pets.domain.LoginUserCaseImpl
import com.appforpets.app4pets.domain.RegisterUseCase
import com.appforpets.app4pets.domain.RegisterUseCaseImpl
import com.appforpets.app4pets.helpers.checkInternetConnection.InternetConnectionHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(authService: AuthService,
                            internetConnectionHelper: InternetConnectionHelper,
                            credentialsDao: CredentialsDao) :
            LoginUserCase = LoginUserCaseImpl(authService, internetConnectionHelper, credentialsDao)

    @Singleton
    @Provides
    fun provideRegisterUseCase(authService: AuthService,
                            internetConnectionHelper: InternetConnectionHelper) :
            RegisterUseCase = RegisterUseCaseImpl(authService, internetConnectionHelper)

}