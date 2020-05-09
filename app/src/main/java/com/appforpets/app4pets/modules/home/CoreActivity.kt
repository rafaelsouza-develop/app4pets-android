package com.appforpets.app4pets.modules.home

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.appforpets.app4pets.App4petsApplication
import com.appforpets.app4pets.R
import com.appforpets.app4pets.data.local.CredentialsDao
import com.appforpets.app4pets.data.local.CredentialsDaoImpl
import com.appforpets.app4pets.helpers.session.UserSessionHelper
import com.appforpets.app4pets.helpers.session.UserSessionHelperImpl
import com.appforpets.app4pets.modules.base.BaseActivity
import com.appforpets.app4pets.modules.login.LoginActivity
import javax.inject.Inject

class CoreActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CoreViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        App4petsApplication.daggerComponent.inject(this)
        viewModel = getViewModel()
        setViewModel(viewModel)
        setupObserverViewState(viewModel)




    }

    override fun onResume() {
        super.onResume()
        viewModel.verifyIsLogger()
    }

    private fun getViewModel(): CoreViewModel {

        return ViewModelProviders
            .of(this, viewModelFactory)
            .get(CoreViewModel::class.java)
    }

    private fun setupObserverViewState(viewModel: CoreViewModel) {
        viewModel.viewState.observe(this, Observer {
            updateView(it)
        })
    }

    private fun updateView(viewState: CoreViewModel.ViewState?) {

        when(viewState) {
            is CoreViewModel.ViewState.VerifyIsLogged -> verifyUserLogged(viewState.isLogged)

        }
    }

    private fun verifyUserLogged(logged: Boolean) {
        if(!logged)
            callLoginScreen()
    }

    fun callLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }


}
