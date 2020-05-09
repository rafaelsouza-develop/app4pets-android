package com.appforpets.app4pets.modules.forgotpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.appforpets.app4pets.App4petsApplication
import com.appforpets.app4pets.R
import com.appforpets.app4pets.modules.base.BaseActivity
import javax.inject.Inject

class ForgotPasswordActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        App4petsApplication.daggerComponent.inject(this)
        viewModel = getViewModel()
        setViewModel(viewModel)
        setupObserverViewState(viewModel)
    }

    private fun getViewModel(): ForgotPasswordViewModel {


        return ViewModelProviders
            .of(this, viewModelFactory)
            .get(ForgotPasswordViewModel::class.java)
    }

    private fun setupObserverViewState(loginViewModel: ForgotPasswordViewModel) {

        loginViewModel.viewState.observe(this, Observer {
            updateViewState(it)
        })
    }

    private fun updateViewState(viewState: ForgotPasswordViewModel.ViewState?) {
        when (viewState) {
            //is LoginViewModel.ViewState.LoginSuccess -> loginSuccess(viewState.user)

        }
    }
}
