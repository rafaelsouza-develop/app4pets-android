package com.appforpets.app4pets.modules.register


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.appforpets.app4pets.App4petsApplication
import com.appforpets.app4pets.R
import com.appforpets.app4pets.modules.base.BaseActivity

import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.edtEmail
import javax.inject.Inject

class RegisterActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initToolbar()
        App4petsApplication.daggerComponent.inject(this)
        viewModel = getViewModel()
        setViewModel(viewModel)
        setupObserverViewState(viewModel)

        setInitialListners()
    }

    private fun  setInitialListners(){
        btnEnter.setOnClickListener {
            sendDataRegister()
        }
    }

    private fun sendDataRegister() {
        val name = edtName.text.toString()
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()
        viewModel.register(email, name, password)
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getViewModel(): RegisterViewModel {


        return ViewModelProviders
            .of(this, viewModelFactory)
            .get(RegisterViewModel::class.java)
    }

    private fun setupObserverViewState(loginViewModel: RegisterViewModel) {

        loginViewModel.viewState.observe(this, Observer {
            updateViewState(it)
        })
    }

    private fun updateViewState(viewState: RegisterViewModel.ViewState?) {
        when (viewState) {
            is RegisterViewModel.ViewState.RegisterSuccess -> registerSuccess(viewState.isRegister)

        }
    }

    private fun registerSuccess(user: Boolean) {

    }
}
