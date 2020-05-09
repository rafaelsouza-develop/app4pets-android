package com.appforpets.app4pets.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appforpets.app4pets.di.ViewModelKey
import com.appforpets.app4pets.domain.TokenUseCase
import com.appforpets.app4pets.helpers.ViewModelFactory
import com.appforpets.app4pets.modules.forgotpassword.ForgotPasswordViewModel
import com.appforpets.app4pets.modules.home.CoreViewModel
import com.appforpets.app4pets.modules.home.ui.account.AccountViewModel
import com.appforpets.app4pets.modules.home.ui.collar.CollarViewModel
import com.appforpets.app4pets.modules.home.ui.pets.PetsViewModel
import com.appforpets.app4pets.modules.login.LoginViewModel
import com.appforpets.app4pets.modules.register.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindsLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CollarViewModel::class)
    internal abstract fun bindsCollarViewModel(collarViewModel: CollarViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    internal abstract fun bindsAccountViewModel(accountViewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PetsViewModel::class)
    internal abstract fun bindsPetsViewModel(petsViewModel: PetsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoreViewModel::class)
    internal abstract fun bindsCoreViewModel(coreViewModel: CoreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    internal abstract fun bindsRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordViewModel::class)
    internal abstract fun bindsForgotPasswordViewModel(forgotPasswordViewModel: ForgotPasswordViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}