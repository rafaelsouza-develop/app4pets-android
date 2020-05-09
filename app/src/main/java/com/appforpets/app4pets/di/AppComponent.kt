package com.appforpets.app4pets.di

import com.appforpets.app4pets.App4petsApplication
import com.appforpets.app4pets.di.modules.*
import com.appforpets.app4pets.modules.forgotpassword.ForgotPasswordActivity
import com.appforpets.app4pets.modules.home.CoreActivity
import com.appforpets.app4pets.modules.home.ui.account.AccountFragment
import com.appforpets.app4pets.modules.home.ui.collar.CollarFragment
import com.appforpets.app4pets.modules.home.ui.pets.PetsFragment
import com.appforpets.app4pets.modules.login.LoginActivity
import com.appforpets.app4pets.modules.register.RegisterActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        HelperModule::class,
        DataRemoteModule::class,
        UseCaseModule::class,
        ViewModelModule::class,
        DataLocalModule::class

    ]
)
interface AppComponent {

    fun inject(app: App4petsApplication)
    fun inject(loginActivity: LoginActivity)
    fun inject(coreActivity: CoreActivity)
    fun inject(accountFragment: AccountFragment)
    fun inject(collarFragment: CollarFragment)
    fun inject(petsFragment: PetsFragment)
    fun inject(registerActivity: RegisterActivity)
    fun inject(forgotPasswordActivity: ForgotPasswordActivity)
}