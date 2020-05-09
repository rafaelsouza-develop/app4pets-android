package com.appforpets.app4pets.di.modules

import com.appforpets.app4pets.data.remote.AuthService
import com.appforpets.app4pets.helpers.RetrofitHelper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class DataRemoteModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = RetrofitHelper.getRetrofit()

    @Singleton
    @Provides
    fun provideAuthServer(retrofit: Retrofit):
            AuthService = retrofit.create(AuthService::class.java)
}