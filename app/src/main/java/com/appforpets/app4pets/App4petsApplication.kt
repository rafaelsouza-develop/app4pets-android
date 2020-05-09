package com.appforpets.app4pets

import android.app.Application
import com.appforpets.app4pets.di.AppComponent
import com.appforpets.app4pets.di.DaggerAppComponent

import com.appforpets.app4pets.di.modules.AppModule

class App4petsApplication: Application() {

    companion object {
        lateinit var context: App4petsApplication
        lateinit var daggerComponent: AppComponent


    }

    override fun onCreate() {
        super.onCreate()
        context = this

        daggerComponent = initDagger(this)
    }

  private fun initDagger(app: App4petsApplication) = DaggerAppComponent
        .builder()
        .appModule(AppModule(app))
        .build()


}