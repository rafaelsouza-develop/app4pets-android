package com.appforpets.app4pets.modules.login


import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.appforpets.app4pets.App4petsApplication
import com.appforpets.app4pets.R
import com.appforpets.app4pets.models.User
import com.appforpets.app4pets.modules.base.BaseActivity
import com.appforpets.app4pets.modules.forgotpassword.ForgotPasswordActivity
import com.appforpets.app4pets.modules.home.CoreActivity
import com.appforpets.app4pets.modules.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        App4petsApplication.daggerComponent.inject(this)
        viewModel = getViewModel()
        setViewModel(viewModel)
        setupObserverViewState(viewModel)

        setListners()
    }

    private fun setListners(){
        btnEnter.setOnClickListener { sentDataLogin() }
        btnCreateAccount.setOnClickListener { callRegisterScreen() }
        btnForgotPassword.setOnClickListener { callForgotPasswordScreen() }
    }

    private fun getViewModel(): LoginViewModel {


        return ViewModelProviders
            .of(this, viewModelFactory)
            .get(LoginViewModel::class.java)
    }

    private fun setupObserverViewState(loginViewModel: LoginViewModel) {

        loginViewModel.viewState.observe(this, Observer {
            updateViewState(it)
        })
    }

    private fun updateViewState(viewState: LoginViewModel.ViewState?) {
        when (viewState) {
            is LoginViewModel.ViewState.LoginSuccess -> loginSuccess(viewState.user)

        }
    }

    private fun sentDataLogin(){
        var email = edtEmail.text.toString()
        var password = edtPassword.text.toString()

        viewModel.login(email, password)
    }

    private fun loginSuccess(user: User) {
        callHomeScreen()
    }

    private fun callHomeScreen() {
        val intent = Intent(this, CoreActivity::class.java)
        this.startActivity(intent)
        this.finish()
    }

    private fun callRegisterScreen() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun callForgotPasswordScreen() {
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }
}
